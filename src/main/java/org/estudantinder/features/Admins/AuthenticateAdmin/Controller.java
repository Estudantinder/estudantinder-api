package org.estudantinder.features.Admins.AuthenticateAdmin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Admins.AuthenticateAdmin.DTO.JwtDTO;
import org.estudantinder.features.Admins.AuthenticateAdmin.DTO.LoginDTO;
import org.estudantinder.features.commom.ErrorResponse;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Controller {

    @Inject
    Feature authenticateAdmin;

    public Response handle(LoginDTO data) throws Exception {
        try {
            JwtDTO returnObject = authenticateAdmin.execute(data);

            return Response.status(Response.Status.OK).entity(returnObject).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel autenticar o admin", error);

        } catch (UnauthorizedException error) {
            return ErrorResponse.handle(401, "Não foi possivel autenticar o admin", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Não foi possivel autenticar o admin", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel autenticar o admin", error);
        }
    }
}
