package com.stnetix.ariaddna.restapiserver.api;

import com.stnetix.ariaddna.restapiserver.model.Credential;
import com.stnetix.ariaddna.restapiserver.model.Session;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-24T10:27:06.657+03:00")

@Controller
public class AuthApiController implements AuthApi {



    public ResponseEntity<Session> authUser(@ApiParam(value = "Authorization user credential." ,required=true )  @Valid @RequestBody Credential user) {
        // do some magic!
        return new ResponseEntity<Session>(HttpStatus.OK);
    }

    public ResponseEntity<Void> logoutSession(@ApiParam(value = "UUID of user session.",required=true ) @PathVariable("uuid") String uuid) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
