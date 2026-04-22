package org.biblioteka.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(configKey = "ipify-api")
public interface IpifyApi {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getPublicIp();
}

