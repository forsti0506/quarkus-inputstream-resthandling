package com.example;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.io.InputStream;

@RestClient
@RegisterRestClient(baseUri = "http://localhost:8080")
@Path("")
@Singleton
public interface TestRestClient {
    @Path("/hello")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    void hello(InputStream is);
}
