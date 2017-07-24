package com.stnetix.ariaddna.restapiserver.api;

import com.stnetix.ariaddna.restapiserver.model.Cloud;

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
public class CloudsApiController implements CloudsApi {



    public ResponseEntity<Cloud> addExternalCloudAccount(@ApiParam(value = "A required information about an external cloud service that a user wants to include to his ariADDna."  )  @Valid @RequestBody Cloud cloud) {
        // do some magic!
        return new ResponseEntity<Cloud>(HttpStatus.OK);
    }

    public ResponseEntity<UUID> deleteExternalCloudAccount(@ApiParam(value = "An external cloud identifier which user has inside his profile. Allows to delete external cloud profile.",required=true ) @PathVariable("cloudUuid") UUID cloudUuid) {
        // do some magic!
        return new ResponseEntity<UUID>(HttpStatus.OK);
    }

}
