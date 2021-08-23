package org.estudantinder.features.Admins.CreateAdmin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Admins.CreateAdmin.DTO.AdminDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createAdmin;

    public Response handle(AdminDTO data, JsonWebToken jwt) throws Exception {
        try {
            createAdmin.execute(data, jwt);

            return Response.status(Response.Status.CREATED).build();

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Não foi possivel criar o admin", error);

        } catch (BadRequestException error) {
            return ErrorResponse.handle(400, "Não foi possivel criar o admin", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Não foi possivel criar o admin", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel criar o admin", error);

        }
    }
}
