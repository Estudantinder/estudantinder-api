package org.estudantinder.features.Statistics.Dashboard;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Statistics.Dashboard.DTO.DashboardDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showDashboardStatistics;

    public Response handle() throws Exception {
        try {
            DashboardDTO dashboardDTO = showDashboardStatistics.execute();

            return Response.status(Response.Status.OK).entity(dashboardDTO).build();
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar os dados da dashboard", error);
        }
    }
}