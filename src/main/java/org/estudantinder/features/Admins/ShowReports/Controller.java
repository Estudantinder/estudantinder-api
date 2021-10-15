package org.estudantinder.features.Admins.ShowReports;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Admins.ShowReports.DTO.UserReportsDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showReports;

    public Response handle() throws Exception {
        try {
            List<UserReportsDTO> reportsList = showReports.execute();

            return Response.status(Response.Status.OK).entity(reportsList).build();
        }catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar os reports", error);
        }
    }
}
