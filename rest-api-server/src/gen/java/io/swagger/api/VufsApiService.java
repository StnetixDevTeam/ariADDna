package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.util.Date;
import io.swagger.model.ErrorModel;
import io.swagger.model.InitialAllocationModel;
import io.swagger.model.Vufs;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-07T13:22:44.504Z")
public abstract class VufsApiService {
    public abstract Response getDiffVUFS(String userUuid,Date lastCreationTime,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getVUFS(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response postAllocateModel(String userUuid,InitialAllocationModel initialAllocationModel,SecurityContext securityContext) throws NotFoundException;
    public abstract Response sendChangesInLFS(Vufs localChanges,String userUuid,SecurityContext securityContext) throws NotFoundException;
}
