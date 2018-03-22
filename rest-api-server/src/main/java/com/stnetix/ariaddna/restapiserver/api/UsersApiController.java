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

import com.stnetix.ariaddna.restapiserver.model.CloudSetPages;
import com.stnetix.ariaddna.restapiserver.model.User;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

@Controller
public class UsersApiController implements UsersApi {

    public ResponseEntity<User> addUser(
            @ApiParam(value = "A new User-object containing all specific information that makes user profile unique.", required = true) @Valid @RequestBody User user) {
        // do some magic!
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    public ResponseEntity<Void> changeUserPassword(
            @ApiParam(value = "UUID of user to fetch.", required = true) @PathVariable("userUuid") String userUuid,
            @ApiParam(value = "Changed user.", required = true) @Valid @RequestBody User user) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUser(
            @ApiParam(value = "UUID of user to delete.", required = true) @PathVariable("userUuid") String userUuid) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<User> findUserByUuid(
            @ApiParam(value = "The UUID of a user to fetch.", required = true) @PathVariable("userUuid") UUID userUuid) {
        // do some magic!
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    public ResponseEntity<CloudSetPages> getExternalCloudAccounts(
            @ApiParam(value = "Providing user's UUID one can access his CloudSet and add retrieve the list of available external cloud accounts.", required = true) @PathVariable("userUuid") UUID userUuid) {
        // do some magic!
        return new ResponseEntity<CloudSetPages>(HttpStatus.OK);
    }

}
