package org.estudantinder.features.Students.Dislikes.DeleteAllDeslikes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createAllDislikes;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            createAllDislikes.execute(jwt);

            return Response.status(Response.Status.OK).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't delete Dislikes", error);

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Couldn't delete Dislikes", error);

        } catch (UnauthorizedException error) {
            return ErrorResponse.handle(401, "Couldn't delete Dislikes", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't delete Dislikes", error);

        }
    }
}
