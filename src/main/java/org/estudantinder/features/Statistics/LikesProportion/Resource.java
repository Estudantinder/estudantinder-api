package org.estudantinder.features.Statistics.LikesProportion;

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

@Path("/statistics")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Statistics", description = "App statistics")
public class Resource {

    @Inject
    Controller likesPropController;

    @GET
    @Path("/likesProportion/${id}")
    @APIResponse(responseCode = "200")
    @Operation(summary = "Return a user's likes proportion")
    public Response getUserLikesProportion(@PathParam("id") Long id) throws Exception {
        return likesPropController.handle(id);
    }
}
