package org.estudantinder.features.Students.Matchs.ShowMatchs;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Students.common.MatchReturn;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showMatchs;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            List<MatchReturn> StudentMatchs = showMatchs.execute(jwt);

            return Response.status(Response.Status.OK).entity(StudentMatchs).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar os matchs", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar os matchs", error);

        }
    }
}