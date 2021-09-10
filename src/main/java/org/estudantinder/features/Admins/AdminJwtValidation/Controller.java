package org.estudantinder.features.Admins.AdminJwtValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature validateJwtUseCase;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            validateJwtUseCase.execute(jwt);

            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel validar o JWT", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Não foi possivel validar o JWT", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel validar o JWT", error);

        }
    }
}
