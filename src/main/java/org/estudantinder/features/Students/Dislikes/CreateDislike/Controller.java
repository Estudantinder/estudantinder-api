package org.estudantinder.features.Students.Dislikes.CreateDislike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createDislike;

    public Response handle(JsonWebToken jwt, Long receiverId) throws Exception {
        try {
            createDislike.execute(jwt, receiverId);

            return Response.status(Response.Status.CREATED).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't update School", error);

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Couldn't update School", error);

        } catch (BadRequestException error) {
            return ErrorResponse.handle(400, "Couldn't update School", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't update School", error);
        }
    }
}
