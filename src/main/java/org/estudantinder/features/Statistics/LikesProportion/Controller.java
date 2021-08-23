package org.estudantinder.features.Statistics.LikesProportion;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showNumberOfUsersInSubject;

    public Response handle(Long userId) throws Exception {
        try {
            LikesProportionDTO likesProportion = showNumberOfUsersInSubject.execute(userId);

            return Response.status(Response.Status.OK).entity(likesProportion).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar a proporção de likes", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar a proporção de likes", error);
        }
    }
}