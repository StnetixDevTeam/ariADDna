package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.CloudsApiService;
import io.swagger.api.factories.CloudsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Cloud;
import io.swagger.model.ErrorModel;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/clouds")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the clouds API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-08T08:47:23.330Z")
public class CloudsApi  {
   private final CloudsApiService delegate = CloudsApiServiceFactory.getCloudsApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows one to add an external cloud account to an existing ariADDna's user. The User MUST be already registered at the cloud service to be added.", response = Cloud.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "New cloud account was added successfully.", response = Cloud.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "This cloud account already exists.", response = Cloud.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Cloud.class) })
    public Response addExternalCloudAccount(@ApiParam(value = "A required information about an external cloud service that a user wants to include to his ariADDna." ) Cloud cloud
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addExternalCloudAccount(cloud,securityContext);
    }
    @DELETE
    @Path("/{cloudUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to delete user's external cloud account.", response = String.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The cloud account was deleted.", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "The specified cloud account was not found.", response = String.class) })
    public Response deleteExternalCloudAccount(@ApiParam(value = "An external cloud identifier which user has inside his profile. Allows to delete external cloud profile.",required=true) @PathParam("cloudUuid") String cloudUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteExternalCloudAccount(cloudUuid,securityContext);
    }
}
