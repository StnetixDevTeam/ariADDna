package io.swagger.api.factories;

import io.swagger.api.StatApiService;
import io.swagger.api.impl.StatApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-08T08:47:23.330Z")
public class StatApiServiceFactory {
    private final static StatApiService service = new StatApiServiceImpl();

    public static StatApiService getStatApi() {
        return service;
    }
}
