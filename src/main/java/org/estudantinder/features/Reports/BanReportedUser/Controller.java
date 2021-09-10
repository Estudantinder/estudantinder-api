package org.estudantinder.features.Reports.BanReportedUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature banReportedUser;

    public Response handle(Long reportId) throws Exception {
        try {
            banReportedUser.execute(reportId);

            return Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel banir o usuário", error);
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel banir o usuário", error);
        }
    }
}
