package org.estudantinder.features.School.ShowSchool;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public List<School> index() {
        return schoolsRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Response showSchools(@PathParam("id") Long id) {
        return showSchoolController.handle(id);
    }
}
