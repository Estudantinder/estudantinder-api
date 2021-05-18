package org.estudantinder.features.Users.UpdateUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Users.UpdateUser.DTO.UserDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature updateUser;

    public Response handle(JsonWebToken jwt, UserDTO data) throws Exception {
        try {
            updateUser.execute(jwt, data);

            return Response.status(Response.Status.OK).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't update User", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't update User", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't update User", error);
        }
    }
}
