package org.estudantinder.features.Subjects.DeleteSubject;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("subjects")
@Tag(name = "Subjects")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    
    @Inject
    Controller deleteSubjectController;

    @DELETE
    @Path("{id}")
    @Transactional
    @APIResponse(responseCode = "200", description = "Successfully Updated")
    @APIResponse(responseCode = "404", description = "Subject ID Not Found")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    @Operation(summary = "Delete given Id Subject")
    public Response deleteSubject(@PathParam("id") Long id) throws Exception {
        return deleteSubjectController.handle(id);
    }

}
