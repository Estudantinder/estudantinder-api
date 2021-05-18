package org.estudantinder.features.Users.AuthenticateUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Users.AuthenticateUser.DTO.JwtDTO;
import org.estudantinder.features.Users.AuthenticateUser.DTO.LoginDTO;
import org.estudantinder.features.commom.ErrorResponse;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Controller {

    @Inject
    Feature authenticateUser;

    public Response handle(LoginDTO data) throws Exception {
        try {
            JwtDTO returnObject = authenticateUser.execute(data);

            return Response.status(Response.Status.OK).entity(returnObject).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't authenticate User", error);

        } catch (UnauthorizedException error) {
            return ErrorResponse.handle(401, "Couldn't authenticate User", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't authenticate User", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't authenticate User", error);
        }
    }
}
