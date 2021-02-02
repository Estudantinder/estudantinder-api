package org.estudantinder.features.Users.UserEmailValidation;

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

@Path("users")
@Tag(name = "Users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    Controller validateEmailController;

    @Path("emailValidation")
    @POST
    @Transactional
    @APIResponse(responseCode = "204", description = "Valid Email")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "409", description = "Email Already In Use")
    @APIResponse(responseCode = "500", description = "Couldn't validate email")
    @Operation(summary = "Check if email is not in use or incorrect")
    public Response createUser(@Valid DTO data) throws Exception {
        return validateEmailController.handle(data);
    }
}