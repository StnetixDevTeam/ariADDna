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

import javax.validation.Valid;

import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.stnetix.ariaddna.restapiserver.model.Credential;
import com.stnetix.ariaddna.restapiserver.model.Session;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

@Controller
public class AuthApiController implements AuthApi {

    public ResponseEntity<Session> authUser(
            @ApiParam(value = "Authorization user credential.", required = true) @Valid @RequestBody Credential user) {
        // do some magic!
        return new ResponseEntity<Session>(HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutSession(
            @ApiParam(value = "UUID of user session.", required = true) @PathVariable("uuid") String uuid) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
