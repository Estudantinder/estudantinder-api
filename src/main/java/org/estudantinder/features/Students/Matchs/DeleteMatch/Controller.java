package org.estudantinder.features.Students.Matchs.DeleteMatch;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteMatch;

    public Response handle(JsonWebToken jwt, Long matchId) throws Exception {
        try {
            deleteMatch.execute(jwt, matchId);

            return Response.status(Response.Status.OK).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't delete Match", error);

        } catch (UnauthorizedException error) {
            return ErrorResponse.handle(401, "Couldn't delete Match", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't delete Match", error);

        }
    }
}
