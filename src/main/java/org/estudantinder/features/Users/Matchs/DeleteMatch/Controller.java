package org.estudantinder.features.Users.Matchs.DeleteMatch;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteSchool;

    public Response handle(JsonWebToken jwt, Long matchId) throws Exception {
        try {
            deleteSchool.execute(jwt, matchId);

            return Response
                .status(Response.Status.OK)
                .build();


        } catch (EntityNotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't delete School";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create School";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
