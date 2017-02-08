package io.swagger.api.factories;

import io.swagger.api.AuthApiService;
import io.swagger.api.impl.AuthApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-08T08:47:23.330Z")
public class AuthApiServiceFactory {
    private final static AuthApiService service = new AuthApiServiceImpl();

    public static AuthApiService getAuthApi() {
        return service;
    }
}
