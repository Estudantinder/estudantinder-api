package org.estudantinder.features.Admins.ShowFullUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showUser;

    public Response handle(Long userId) throws Exception {
        try {
            FullUserDTO fullUser = showUser.execute(userId);

            return Response.status(Response.Status.OK).entity(fullUser).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar o usuário", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar o usuário", error);

        }
    }
}
