package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.VufsApiService;
import io.swagger.api.factories.VufsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.CloudSpaseSet;
import io.swagger.model.ErrorModel;
import io.swagger.model.Statistics;
import io.swagger.model.Vufs;
import io.swagger.model.HelthStatistis;
import io.swagger.model.AllocateModel;

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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-26T14:00:48.377Z")
public class VufsApi  {
   private final VufsApiService delegate = VufsApiServiceFactory.getVufsApi();

    @GET
    @Path("/space/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get availability space on each cloud.", response = CloudSpaseSet.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Clouds availability space.", response = CloudSpaseSet.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad user UUID.", response = CloudSpaseSet.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = CloudSpaseSet.class) })
    public Response getAvailableSpace(@ApiParam(value = "User UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAvailableSpace(userUuid,securityContext);
    }
    @GET
    @Path("/stat/{cloudUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get statistic object about selected cloud.", response = Statistics.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Cloud statistic.", response = Statistics.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad cloud UUID.", response = Statistics.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Statistics.class) })
    public Response getCloudStatistic(@ApiParam(value = "Cloud UUID.",required=true) @PathParam("cloudUuid") String cloudUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getCloudStatistic(cloudUuid,securityContext);
    }
    @GET
    @Path("/snap/diff/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get difference of previous snapshot and actual.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Difference VUFS snapshot of current user.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad user UUID.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response getDiffVUFS(@ApiParam(value = "Current user UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getDiffVUFS(userUuid,securityContext);
    }
    @GET
    @Path("/stat/helth/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get helth-check statistic about users Clouds.", response = HelthStatistis.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Clouds helth-check statistic.", response = HelthStatistis.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad user UUID.", response = HelthStatistis.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = HelthStatistis.class) })
    public Response getHelthCheckStat(@ApiParam(value = "User UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getHelthCheckStat(userUuid,securityContext);
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
    @Path("/allocation")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to post file allocate strategy from client to server.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Allocate strategy was send to server. As respose - difference VUFS object.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response postAllocateModel(@ApiParam(value = "File allocate strategy." ,required=true) AllocateModel allocateModel
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.postAllocateModel(allocateModel,securityContext);
    }
    @POST
    @Path("/stat")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to post statistic from client to server about cloud.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Statistics was send to server.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = void.class) })
    public Response postCloudStat(@ApiParam(value = "Cloud statistic typed object." ,required=true) Statistics cloudStatistic
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.postCloudStat(cloudStatistic,securityContext);
    }
    @POST
    @Path("/snap/diff/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to send changes in local file storage to server.", response = Vufs.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Checked snapshot changes.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 409, message = "Changes was not found in clouds.", response = Vufs.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = Vufs.class) })
    public Response sendChangesInLFS(@ApiParam(value = "VUFS snapshot typed object with changed in local file storage." ,required=true) Vufs localChanges
,@ApiParam(value = "Current user UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.sendChangesInLFS(localChanges,userUuid,securityContext);
    }
}
