package org.estudantinder.features.Statistics.FilterUsage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("statistics")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Statistics", description = "App statistics")
public class Resource {

    @Inject 
    Controller showFilterUsageController;

    @GET
    @Path("filterUsage")
    @Operation(summary = "Get the quantity of uses for each filter")
    public Response getSubjectStatistics() throws Exception {
        return showFilterUsageController.handle();
    }

}
