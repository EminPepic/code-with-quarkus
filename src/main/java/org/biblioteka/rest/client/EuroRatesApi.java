package org.biblioteka.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.biblioteka.model.CurrencyResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/rates")
@RegisterRestClient(configKey = "eurorates-api")
public interface EuroRatesApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    CurrencyResponse getRate(@QueryParam("from") String from, @QueryParam("to") String to);
}

