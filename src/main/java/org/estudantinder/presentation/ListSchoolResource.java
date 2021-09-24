package org.estudantinder.presentation;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.domain.models.School;
import org.estudantinder.main.ListSchoolService;
import org.estudantinder.shared.exception.ErrorResponse;

@Path("schools")
@Tag(name = "School")
@Produces(MediaType.APPLICATION_JSON)
public class ListSchoolResource {
    
    @Inject
    ListSchoolService listSchoolService;

    @GET
    @Path("/{schoolId}")
    @Operation(summary = "List school by id")
    @APIResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = School[].class)
        )
    )
    @APIResponse(
        responseCode = "404",
        description = "School not found",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = ErrorResponse.class),
            example = 
            "{\n" +
                "\"code\":\"404\",\n" +
                "\"message\":\"Escola não encontrada\"\n" +
            "}"
        )
    )
    public Response handle(@PathParam("schoolId") String schoolIdString) {
        UUID schoolId = UUID.fromString(schoolIdString);
        return Response
            .status(200)
            .entity(listSchoolService.handle(schoolId))
            .build();
    }
}
