package org.estudantinder.features.Users.UserEmailValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature validateEmailUseCase;

    public Response handle(DTO data) throws Exception {
        try {
            validateEmailUseCase.execute(data);

            return Response.status(Response.Status.NO_CONTENT).build();

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Couldn't validate email", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't validate email", error);
        }
    }
}
