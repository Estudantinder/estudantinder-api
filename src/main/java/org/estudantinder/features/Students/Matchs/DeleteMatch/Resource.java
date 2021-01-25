package org.estudantinder.features.Students.Matchs.DeleteMatch;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("students/matchs")
@Tag(name = "Students")
@Produces(MediaType.APPLICATION_JSON)
@SecurityScheme(securitySchemeName = "jwt", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "jwt")
public class Resource {
    
    @Inject
    JsonWebToken jwt;
    
    @Inject
    Controller deleteMatchController;

    @DELETE
    @Path("{id}")
    @Transactional
    @RolesAllowed("User")
    @SecurityRequirement(name = "jwt")
    @APIResponse(responseCode = "200", description = "Match Successfully Deleted")
    @APIResponse(responseCode = "401", description = "Unauthorized, Match Ins't Yours")
    @APIResponse(responseCode = "404", description = "Match ID Not Found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Delete given Id Match")
    public Response deleteMatch(@Context SecurityContext ctx, @PathParam("id") Long matchId) throws Exception {
        return deleteMatchController.handle(jwt, matchId);
    }

}
