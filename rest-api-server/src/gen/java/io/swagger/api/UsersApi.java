package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UsersApiService;
import io.swagger.api.factories.UsersApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.CloudSetPages;
import io.swagger.model.ErrorModel;
import io.swagger.model.User;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/users")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the users API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-08T08:47:23.330Z")
public class UsersApi  {
   private final UsersApiService delegate = UsersApiServiceFactory.getUsersApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows one to create a new user.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "A new user was successfully created.", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Such user already exists.", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = User.class) })
    public Response addUser(@ApiParam(value = "A new User-object containing all specific information that makes user profile unique." ,required=true) User user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addUser(user,securityContext);
    }
    @PUT
    @Path("/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Changing user password.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "User updated.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = void.class) })
    public Response changeUserPassword(@ApiParam(value = "UUID of user to fetch.",required=true) @PathParam("userUuid") String userUuid
,@ApiParam(value = "Changed user." ,required=true) User user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.changeUserPassword(userUuid,user,securityContext);
    }
    @DELETE
    @Path("/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Deleting user.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "User deleted.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User not found.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = void.class) })
    public Response deleteUser(@ApiParam(value = "UUID of user to delete.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteUser(userUuid,securityContext);
    }
    @GET
    @Path("/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "This operation allows one to get back information about certain user providing his UUID as a path parameter.", response = User.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The user was found.", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "The user was not found.", response = User.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = User.class) })
    public Response findUserByUuid(@ApiParam(value = "The UUID of a user to fetch.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.findUserByUuid(userUuid,securityContext);
    }
    @GET
    @Path("/{userUuid}/clouds")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Returns a list of clouds which a certain user has connected to his ariADDna account.", response = CloudSetPages.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The user's accounts were found.", response = CloudSetPages.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "User does not exist.", response = CloudSetPages.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = CloudSetPages.class) })
    public Response getExternalCloudAccounts(@ApiParam(value = "Providing user's UUID one can access his CloudSet and add retrieve the list of available external cloud accounts.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getExternalCloudAccounts(userUuid,securityContext);
    }
}
