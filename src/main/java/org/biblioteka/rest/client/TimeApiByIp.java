package org.biblioteka.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.biblioteka.model.TimezoneByIpResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/time/current")
@RegisterRestClient(configKey = "timeapi-api")
public interface TimeApiByIp {

    @GET
    @Path("/ip")
    @Produces(MediaType.APPLICATION_JSON)
    TimezoneByIpResponse getTimezoneByIp(@QueryParam("ipAddress") String ipAddress);
}

