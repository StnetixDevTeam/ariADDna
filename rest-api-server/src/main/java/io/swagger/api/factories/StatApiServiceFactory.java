package io.swagger.api.factories;

import io.swagger.api.StatApiService;
import io.swagger.api.impl.StatApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-13T15:04:21.309Z")
public class StatApiServiceFactory {
    private final static StatApiService service = new StatApiServiceImpl();

    public static StatApiService getStatApi() {
        return service;
    }
}
