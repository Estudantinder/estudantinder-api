package org.estudantinder.presentation;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.domain.dto.CreateSchoolDTO;
import org.estudantinder.main.CreateSchoolService;

@Path("/schools")
@Tag(name = "Schools")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateSchoolResource {

    @Inject
    CreateSchoolService createSchoolService;

    @POST
    @Transactional
    @Operation(summary = "Create a new School")
    public Response handle(@RequestBody CreateSchoolDTO createSchoolDTO) {
        return Response.status(201).entity(createSchoolService.handle(createSchoolDTO)).build();
    }
}
