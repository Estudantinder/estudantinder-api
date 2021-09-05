package org.estudantinder.features.Statistics.FilterUsage;


import java.util.List;

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
            List<FilterUsageDTO> filterUsageDTOs = showFilterUsage.execute();

            return Response.status(Response.Status.OK).entity(filterUsageDTOs).build();
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar o numero de usuarios registrados", error);
        }
    }
}