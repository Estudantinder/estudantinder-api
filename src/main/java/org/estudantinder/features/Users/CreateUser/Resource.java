package org.estudantinder.features.Users.CreateUser;

import javax.inject.Inject;
import javax.transaction.Transactional;
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
import org.estudantinder.features.Users.CreateUser.DTO.UserDTO;

@Path("users")
@Tag(name = "Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    Controller createUserController;

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "409", description = "Email Already In Use")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Create a new User")
    public Response createUser(@Valid UserDTO data) throws Exception {
        return createUserController.handle(data);
    }
}