package com.dkothandan.prismatic.utils;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class HttpClientHelper<T> {

    public T get(String url, Class clazz) {
        int tries = 0;

        while (tries < 2) {
            try {
                Client client = ResteasyClientBuilder.newClient();
                WebTarget target = client.target(url);
                Response response = target.request().get();
                //Read output in string format
                T value = (T) response.readEntity(clazz);
                response.close();
                client.close();
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            }

            tries++;
        }

        return null;
    }
}
