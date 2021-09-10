package org.estudantinder.features.Students.Likes.CreateLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Students.common.MatchReturn;

import org.estudantinder.features.commom.ErrorResponse;


@ApplicationScoped
public class Controller {

    @Inject
    Feature createLike;

    public Response handle(JsonWebToken jwt, Long receiverId) throws Exception {
        try {
            MatchReturn matchedStudent = createLike.execute(jwt, receiverId);

            return Response.status(Response.Status.CREATED).entity(matchedStudent).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel criar o like", error);

        } catch (BadRequestException error) {
            return ErrorResponse.handle(400, "Não foi possivel criar o like", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel criar o like", error);

        }
    }
}
