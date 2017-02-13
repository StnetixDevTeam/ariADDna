package io.swagger.api.factories;

import io.swagger.api.VufsApiService;
import io.swagger.api.impl.VufsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-13T15:04:21.309Z")
public class VufsApiServiceFactory {
    private final static VufsApiService service = new VufsApiServiceImpl();

    public static VufsApiService getVufsApi() {
        return service;
    }
}
