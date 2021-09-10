package org.estudantinder.features.Users.ShowUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showUser;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            DTO dto = showUser.execute(jwt);

            return Response.status(Response.Status.OK).entity(dto).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar o usuário", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar o usuário", error);

        }
    }
}
