package io.swagger.api.factories;

import io.swagger.api.StatApiService;
import io.swagger.api.impl.StatApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-07T13:22:44.504Z")
public class StatApiServiceFactory {
    private final static StatApiService service = new StatApiServiceImpl();

    public static StatApiService getStatApi() {
        return service;
    }
}
