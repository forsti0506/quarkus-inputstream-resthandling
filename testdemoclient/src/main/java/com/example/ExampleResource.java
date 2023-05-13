package com.example;


import io.smallrye.common.annotation.NonBlocking;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Path("/hello")
public class ExampleResource {

    @Inject
    EntityManager em;
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Transactional
    public void hello(InputStream is) {
        try {
            var liEnd = is.readNBytes(2);
            while (liEnd.length > 0){
                var test = new TestEntitiy();

                test.setName(new String(liEnd));
                em.persist(test);
                liEnd = is.readNBytes(2);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}