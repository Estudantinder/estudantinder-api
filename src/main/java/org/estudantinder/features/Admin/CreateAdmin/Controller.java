package org.estudantinder.features.Admin.CreateAdmin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Admin.CreateAdmin.DTO.AdminDTO;
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
            return ErrorResponse.handle(409, "Couldn't create Admin", error);

        } catch (BadRequestException error) {
            return ErrorResponse.handle(400, "Couldn't create Admin", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't create Admin", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't create Admin", error);

        }
    }
}
