package io.swagger.api.factories;

import io.swagger.api.CloudsApiService;
import io.swagger.api.impl.CloudsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-08T08:47:23.330Z")
public class CloudsApiServiceFactory {
    private final static CloudsApiService service = new CloudsApiServiceImpl();

    public static CloudsApiService getCloudsApi() {
        return service;
    }
}
