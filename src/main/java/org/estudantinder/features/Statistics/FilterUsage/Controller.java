package org.estudantinder.features.Statistics.FilterUsage;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showFilterUsage;

    public Response handle() throws Exception {
        try {
            FilterUsageDTO filterUsage = showFilterUsage.execute();

            return Response.status(Response.Status.OK).entity(filterUsage).build();
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't show number of users registered", error);
        }
    }
}