package io.swagger.api.factories;

import io.swagger.api.VufsApiService;
import io.swagger.api.impl.VufsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-26T14:00:48.377Z")
public class VufsApiServiceFactory {
    private final static VufsApiService service = new VufsApiServiceImpl();

    public static VufsApiService getVufsApi() {
        return service;
    }
}
