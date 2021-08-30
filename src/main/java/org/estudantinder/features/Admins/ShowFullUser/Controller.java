package org.estudantinder.features.Admins.ShowFullUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.entities.User;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showUser;

    public Response handle(Long userId) throws Exception {
        try {
            User user = showUser.execute(userId);

            return Response.status(Response.Status.OK).entity(user).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar o usuário", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar o usuário", error);

        }
    }
}
