package com.example;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;

public class MyInputStream extends InputStream {


    EntityManager em;

    public MyInputStream(String input, EntityManager em) {
        var test = new TestEntitiy();
        this.em = em;


        test.setName(new String(input));
        em.persist(test);
    }

    @Override
    public int read() throws IOException {
        var results = em.createQuery("SELECT a FROM TestEntitiy a", TestEntitiy.class).getResultList();

        return results.size();
    }
}
