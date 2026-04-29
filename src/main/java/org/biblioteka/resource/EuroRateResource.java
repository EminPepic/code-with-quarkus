package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.biblioteka.model.CurrencyResponse;
import org.biblioteka.model.Student;
import org.biblioteka.rest.client.EuroRatesApi;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class EuroRateResource {

    @Inject
    @RestClient
    EuroRatesApi euroRatesApi;

    @Inject
    jakarta.persistence.EntityManager em;

    @GET 
    @Path("/currencyConversion")
    @Transactional
    @RolesAllowed({ "admin", "Admin" })
    public Response currencyConversion(
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("value") Double value,
            @QueryParam("userId") Long userId) {
        if (userId == null) {
            throw new BadRequestException("Query param 'userId' is required");
        }
        if (from == null || from.isBlank()) {
            throw new BadRequestException("Query param 'from' is required");
        }
        if (to == null || to.isBlank()) {
            throw new BadRequestException("Query param 'to' is required");
        }
        if (value == null) {
            throw new BadRequestException("Query param 'value' is required");
        }

        Student student = em.find(Student.class, userId);
        if (student == null) {
            throw new NotFoundException("Student with id " + userId + " not found");
        }

        CurrencyResponse currencyResponse = euroRatesApi.getRate(from, to);
        currencyResponse.setStudent(student);
        currencyResponse.setValue(value);
        currencyResponse.setConvertedValue(currencyResponse.getValue() * currencyResponse.getRate());

        em.persist(currencyResponse);

        return Response.ok().entity(currencyResponse).build();
    }
    
}
