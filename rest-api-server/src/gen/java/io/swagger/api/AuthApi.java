package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.AuthApiService;
import io.swagger.api.factories.AuthApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Credential;
import io.swagger.model.ErrorModel;
import io.swagger.model.Session;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/auth")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the auth API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-13T15:04:21.309Z")
public class AuthApi  {
   private final AuthApiService delegate = AuthApiServiceFactory.getAuthApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Creating new user session.", response = Session.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "User session.", response = Session.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "User not found on server.", response = Session.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Session.class) })
    public Response authUser(@ApiParam(value = "Authorization user credential." ,required=true) Credential user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.authUser(user,securityContext);
    }
    @PUT
    @Path("/{uuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Closing user session.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Sesson closed.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Session not found.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = void.class) })
    public Response logoutSession(@ApiParam(value = "UUID of user session.",required=true) @PathParam("uuid") String uuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.logoutSession(uuid,securityContext);
    }
}
