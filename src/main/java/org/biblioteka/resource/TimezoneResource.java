package org.biblioteka.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.biblioteka.model.Student;
import org.biblioteka.model.TimezoneByIpResponse;
import org.biblioteka.model.VremenskaZona;
import org.biblioteka.rest.client.IpifyApi;
import org.biblioteka.rest.client.TimeApiByIp;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TimezoneResource {

    @Inject
    @RestClient
    IpifyApi ipifyApi;

    @Inject
    @RestClient
    TimeApiByIp timeApiByIp;

    @Inject
    jakarta.persistence.EntityManager em;

    @GET
    @Path("/getTimezoneByIP")
    @Transactional
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        Student student = em.find(Student.class, userId);
        if (student == null) {
            throw new NotFoundException("Student with id " + userId + " not found");
        }

        String ip = ipifyApi.getPublicIp().trim();
        TimezoneByIpResponse timezone = timeApiByIp.getTimezoneByIp(ip);

        VremenskaZona vremenskaZona = new VremenskaZona();
        vremenskaZona.setStudent(student);
        vremenskaZona.setIpAddress(ip);

        vremenskaZona.setYear(timezone.year);
        vremenskaZona.setMonth(timezone.month);
        vremenskaZona.setDay(timezone.day);
        vremenskaZona.setHour(timezone.hour);
        vremenskaZona.setMinute(timezone.minute);
        vremenskaZona.setSeconds(timezone.seconds);
        vremenskaZona.setMilliSeconds(timezone.milliSeconds);

        vremenskaZona.setDateTime(timezone.dateTime);
        vremenskaZona.setDate(timezone.date);
        vremenskaZona.setTime(timezone.time);
        vremenskaZona.setTimeZone(timezone.timeZone);
        vremenskaZona.setDayOfWeek(timezone.dayOfWeek);
        vremenskaZona.setDstActive(timezone.dstActive);

        student.getVremenskeZone().add(vremenskaZona);
        em.persist(vremenskaZona);

        return Response.ok().entity(timezone).build();
    }
}
