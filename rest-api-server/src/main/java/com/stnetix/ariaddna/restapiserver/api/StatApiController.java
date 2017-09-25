package com.stnetix.ariaddna.restapiserver.api;

import com.stnetix.ariaddna.restapiserver.model.StatisticSet;
import java.util.UUID;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

@Controller
public class StatApiController implements StatApi {



    public ResponseEntity<StatisticSet> getCloudStatisticSet(@ApiParam(value = "User UUID.",required=true ) @PathVariable("userUuid") UUID userUuid) {
        // do some magic!
        return new ResponseEntity<StatisticSet>(HttpStatus.OK);
    }

    public ResponseEntity<StatisticSet> getHealthCheckStat(@ApiParam(value = "User UUID.",required=true ) @PathVariable("userUuid") UUID userUuid) {
        // do some magic!
        return new ResponseEntity<StatisticSet>(HttpStatus.OK);
    }

    public ResponseEntity<Void> postCloudStatSet(@ApiParam(value = "User UUID.",required=true ) @PathVariable("userUuid") UUID userUuid,
        @ApiParam(value = "Cloud statistic set typed object." ,required=true )  @Valid @RequestBody StatisticSet cloudStatisticSet) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
