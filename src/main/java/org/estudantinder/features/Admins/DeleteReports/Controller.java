package org.estudantinder.features.Admins.DeleteReports;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteUserReports;

    public Response handle(Long userId) throws Exception {
        try {
            deleteUserReports.execute(userId);

            return Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel deletar o report", error);
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel deletar o report", error);
        }
    }
}
