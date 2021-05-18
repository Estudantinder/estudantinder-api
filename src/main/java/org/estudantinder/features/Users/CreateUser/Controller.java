package org.estudantinder.features.Users.CreateUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Users.CreateUser.DTO.UserDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createUser;

    public Response handle(UserDTO data) throws Exception {
        try {
            createUser.execute(data);

            return Response.status(Response.Status.CREATED).entity(data).build();

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Couldn't create User", error);

        } catch (BadRequestException error) {
            return ErrorResponse.handle(400, "Couldn't create User", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't create User", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't create User", error);

        }
    }
}
