package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.StatApiService;
import io.swagger.api.factories.StatApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.ErrorModel;
import io.swagger.model.StatisticSet;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/stat")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the stat API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-13T15:04:21.309Z")
public class StatApi  {
   private final StatApiService delegate = StatApiServiceFactory.getStatApi();

    @GET
    @Path("/vufs/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get statistic object about clouds.", response = StatisticSet.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Clouds statistic.", response = StatisticSet.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad cloud UUID.", response = StatisticSet.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = StatisticSet.class) })
    public Response getCloudStatisticSet(@ApiParam(value = "User UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getCloudStatisticSet(userUuid,securityContext);
    }
    @GET
    @Path("/vufs/health/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get health-check statistic about users Clouds.", response = StatisticSet.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Clouds health-check statistic.", response = StatisticSet.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Bad user UUID.", response = StatisticSet.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = StatisticSet.class) })
    public Response getHealthCheckStat(@ApiParam(value = "User UUID.",required=true) @PathParam("userUuid") String userUuid
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getHealthCheckStat(userUuid,securityContext);
    }
    @POST
    @Path("/vufs/{userUuid}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to post statistic from client to server about clouds.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Statistics was send to server.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error.", response = void.class) })
    public Response postCloudStatSet(@ApiParam(value = "User UUID.",required=true) @PathParam("userUuid") String userUuid
,@ApiParam(value = "Cloud statistic set typed object." ,required=true) StatisticSet cloudStatisticSet
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.postCloudStatSet(userUuid,cloudStatisticSet,securityContext);
    }
}
