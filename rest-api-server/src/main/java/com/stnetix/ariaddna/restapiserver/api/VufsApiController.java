package com.stnetix.ariaddna.restapiserver.api;

import com.stnetix.ariaddna.restapiserver.model.InitialAllocationModel;
import java.util.UUID;
import com.stnetix.ariaddna.restapiserver.model.Vufs;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

@Controller
public class VufsApiController implements VufsApi {



    public ResponseEntity<Vufs> getDiffVUFS(@ApiParam(value = "Current user UUID.",required=true ) @PathVariable("userUuid") UUID userUuid,
        @ApiParam(value = "FromDateTime of current VUFS snapshot",required=true ) @PathVariable("dateTime") Long dateTime) {
        // do some magic!
        return new ResponseEntity<Vufs>(HttpStatus.OK);
    }

    public ResponseEntity<Vufs> getVUFS(@ApiParam(value = "Current user UUID.",required=true ) @PathVariable("userUuid") UUID userUuid) {
        // do some magic!
        return new ResponseEntity<Vufs>(HttpStatus.OK);
    }

    public ResponseEntity<Vufs> postAllocateModel(@ApiParam(value = "User UUID.",required=true ) @PathVariable("userUuid") UUID userUuid,
        @ApiParam(value = "File allocation strategy." ,required=true )  @Valid @RequestBody InitialAllocationModel initialAllocationModel) {
        // do some magic!
        return new ResponseEntity<Vufs>(HttpStatus.OK);
    }

    public ResponseEntity<Vufs> sendChangesInLFS(@ApiParam(value = "VUFS snapshot typed object with changed in local file storage with empty Allocation model." ,required=true )  @Valid @RequestBody Vufs localChanges,
        @ApiParam(value = "Current user UUID.",required=true ) @PathVariable("userUuid") UUID userUuid) {
        // do some magic!
        return new ResponseEntity<Vufs>(HttpStatus.OK);
    }

}
