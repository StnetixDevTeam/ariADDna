package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Cloud;
import io.swagger.model.ErrorModel;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-07T13:22:44.504Z")
public abstract class CloudsApiService {
    public abstract Response addExternalCloudAccount(Cloud cloud,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteExternalCloudAccount(String cloudUuid,SecurityContext securityContext) throws NotFoundException;
}
