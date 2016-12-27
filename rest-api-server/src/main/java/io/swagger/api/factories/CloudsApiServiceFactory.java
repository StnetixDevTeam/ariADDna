package io.swagger.api.factories;

import io.swagger.api.CloudsApiService;
import io.swagger.api.impl.CloudsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-26T14:00:48.377Z")
public class CloudsApiServiceFactory {
    private final static CloudsApiService service = new CloudsApiServiceImpl();

    public static CloudsApiService getCloudsApi() {
        return service;
    }
}
