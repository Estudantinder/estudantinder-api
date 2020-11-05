package org.estudantinder.features.School.DeleteSchool;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("school")
@Tag(name = "School")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    
    @Inject
    Controller deleteSchoolController;

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteSchool(@PathParam("id") Long id) throws Exception {
        return deleteSchoolController.handle(id);
    }

}
