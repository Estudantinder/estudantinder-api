package org.estudantinder.schools.presentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.schools.main.ListSchoolsService;

@Path("schools")
@Tag(name = "School")
@Produces(MediaType.APPLICATION_JSON)
public class ListSchoolsResource {
    
    @Inject
    ListSchoolsService listSchoolsService;

    @GET
    @Operation(summary = "List all schools")
    @APIResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = School[].class)
        )
    )
    public Response handle() {
        return Response
            .status(200)
            .entity(listSchoolsService.handle())
            .build();
    }
}