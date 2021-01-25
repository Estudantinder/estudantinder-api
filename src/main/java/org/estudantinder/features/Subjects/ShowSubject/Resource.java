package org.estudantinder.features.Subjects.ShowSubject;

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
import org.estudantinder.entities.Subject;
import org.estudantinder.repositories.SubjectsRepository;

@Path("subjects")
@Tag(name = "Subjects")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    
    @Inject
    SubjectsRepository subjectsRepository;

    @Inject
    Controller showSubjectController;

    @GET
    @Operation(summary = "Show all Subjects")
    public List<Subject> index() throws Exception {
        return subjectsRepository.listAll();
    }

    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200", description = "OK")
    @APIResponse(responseCode = "404", description = "Subject ID Not Found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Show given Id Subject")
    public Response showSubjects(@PathParam("id") Long id) throws Exception {
        return showSubjectController.handle(id);
    }
}
