package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.VufsApiService;
import io.swagger.api.factories.VufsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.ErrorModel;
import io.swagger.model.InitialAllocationModel;
import io.swagger.model.Vufs;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/vufs")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the vufs API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-08T08:47:23.330Z")
public class VufsApi  {
   private final VufsApiService delegate = VufsApiServiceFactory.getVufsApi();

    @GET
    @Path("/snap/diff/{userUuid}/{year}/{month}/{day}/{hour}/{minute}/{second}/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get difference of previous snapshot and actual.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Difference VUFS snapshot of current user.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad user UUID.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response getDiffVUFS(@ApiParam(value = "Current user UUID.",required=true) @PathParam("userUuid") String userUuid
,@ApiParam(value = "Year of fromDateTime of current VUFS snapshot",required=true) @PathParam("year") Integer year
,@ApiParam(value = "Month of fromDateTime of current VUFS snapshot",required=true) @PathParam("month") Integer month
,@ApiParam(value = "Day of fromDateTime of current VUFS snapshot",required=true) @PathParam("day") Integer day
,@ApiParam(value = "Hour of fromDateTime of current VUFS snapshot",required=true) @PathParam("hour") Integer hour
,@ApiParam(value = "Minute of fromDateTime of current VUFS snapshot",required=true) @PathParam("minute") Integer minute
,@ApiParam(value = "Second of fromDateTime of current VUFS snapshot",required=true) @PathParam("second") Integer second
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getDiffVUFS(userUuid,year,month,day,hour,minute,second,securityContext);
    }
    @GET
    @Path("/snap/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get snapshot vufs.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "VUFS snapshot of current user.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad user UUID.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response getVUFS(@ApiParam(value = "Current user UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getVUFS(userUuid,securityContext);
    }
    @POST
    @Path("/allocation/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to post file allocate strategy from client to server.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Allocation strategy was send to server. As respose - difference VUFS object.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response postAllocateModel(@ApiParam(value = "User UUID.",required=true) @PathParam("userUuid") String userUuid
,@ApiParam(value = "File allocation strategy." ,required=true) InitialAllocationModel initialAllocationModel
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.postAllocateModel(userUuid,initialAllocationModel,securityContext);
    }
    @POST
    @Path("/snap/diff/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to send changes in local file storage to server with empty Allocation model and as response get Vufs object with Allocation model.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Checked snapshot changes.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Changes was not found in clouds.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response sendChangesInLFS(@ApiParam(value = "VUFS snapshot typed object with changed in local file storage with empty Allocation model." ,required=true) Vufs localChanges
,@ApiParam(value = "Current user UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.sendChangesInLFS(localChanges,userUuid,securityContext);
    }
}
