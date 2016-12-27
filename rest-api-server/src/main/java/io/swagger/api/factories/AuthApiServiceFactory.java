package io.swagger.api.factories;

import io.swagger.api.AuthApiService;
import io.swagger.api.impl.AuthApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-26T14:00:48.377Z")
public class AuthApiServiceFactory {
    private final static AuthApiService service = new AuthApiServiceImpl();

    public static AuthApiService getAuthApi() {
        return service;
    }
}
