package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.User;
import io.swagger.model.ErrorModel;
import io.swagger.model.CloudSetPages;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-26T14:00:48.377Z")
public abstract class UsersApiService {
    public abstract Response addUser(User user,SecurityContext securityContext) throws NotFoundException;
    public abstract Response changeUserPassword(String userUuid,User user,SecurityContext securityContext) throws NotFoundException;
    public abstract Response deleteUser(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response findUserByUuid(String userUuid,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getExternalCloudAccounts(String userUuid,SecurityContext securityContext) throws NotFoundException;
}
