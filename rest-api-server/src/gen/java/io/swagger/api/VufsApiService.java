package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.CloudSpaseSet;
import io.swagger.model.ErrorModel;
import io.swagger.model.Statistics;
import io.swagger.model.Vufs;
import io.swagger.model.HelthStatistis;
import io.swagger.model.AllocateModel;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-26T14:00:48.377Z")
public abstract class VufsApiService {
    public abstract Response getAvailableSpace(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getCloudStatistic(String cloudUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getDiffVUFS(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getHelthCheckStat(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getVUFS(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response postAllocateModel(AllocateModel allocateModel,SecurityContext securityContext) throws NotFoundException;
    public abstract Response postCloudStat(Statistics cloudStatistic,SecurityContext securityContext) throws NotFoundException;
    public abstract Response sendChangesInLFS(Vufs localChanges,String userUuid,SecurityContext securityContext) throws NotFoundException;
}
