package org.estudantinder.features.School.ShowSchool;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.entities.School;
import org.estudantinder.repositories.SchoolsRepository;

@Path("school")
@Tag(name = "School")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    
    @Inject
    SchoolsRepository schoolsRepository;

    @Inject
    Controller showSchoolController;

    @GET
    @Operation(summary = "Show all Schools")
    public List<School> index() throws Exception {
        return schoolsRepository.listAll();
    }

    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "School ID Not Found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Show given Id School")
    public Response showSchools(@PathParam("id") Long id) throws Exception {
        return showSchoolController.handle(id);
    }
}
