package org.estudantinder.features.Users.DeleteUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature banReportedUser;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            banReportedUser.execute(jwt);

            return Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel banir o usuário", error);
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel banir o usuário", error);
        }
    }
}
