package com.example;

import io.smallrye.common.annotation.NonBlocking;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Path("/hello")
@Transactional
public class ExampleResource {

    @RestClient
    TestRestClient testRestClient;

    @Inject
    EntityManager em;

    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public String hello() {
        InputStream is = new MyInputStream("helloooooooooooooooooo", em);
        testRestClient.hello(is);
        byte[] liEnd = new byte[0];
        try {
            liEnd = is.readNBytes(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (liEnd.length > 0){
            try {
                liEnd = is.readNBytes(2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("yess")
    @Produces("application/json")
    @Consumes("application/json")
    public String yeesss() {
        return "hello";
    }
}