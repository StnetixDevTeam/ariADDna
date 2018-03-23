/*
 * Copyright (c) 2018 stnetix.com. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.stnetix.ariaddna.restapiserver.api;

import java.util.UUID;

import javax.validation.Valid;

import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.stnetix.ariaddna.restapiserver.model.Cloud;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

@Controller
public class CloudsApiController implements CloudsApi {

    public ResponseEntity<Cloud> addExternalCloudAccount(
            @ApiParam(value = "A required information about an external cloud service that a user wants to include to his ariADDna.") @Valid @RequestBody Cloud cloud) {
        // do some magic!
        return new ResponseEntity<Cloud>(HttpStatus.OK);
    }

    public ResponseEntity<UUID> deleteExternalCloudAccount(
            @ApiParam(value = "An external cloud identifier which user has inside his profile. Allows to delete external cloud profile.", required = true) @PathVariable("cloudUuid") UUID cloudUuid) {
        // do some magic!
        return new ResponseEntity<UUID>(HttpStatus.OK);
    }

}
