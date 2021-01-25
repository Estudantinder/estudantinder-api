package org.estudantinder.features.Users.AuthenticateUser;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.features.Users.AuthenticateUser.DTO.LoginDTO;

@Path("users")
@Tag(name = "Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    Controller authenticateUserController;

    @POST
    @Path("login")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "401", description = "Wrong Password")
    @APIResponse(responseCode = "404", description = "User Not Found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Authenticate a User (Login)")
    public Response createUser(@Valid LoginDTO data) throws Exception {
        return authenticateUserController.handle(data);
    }

}