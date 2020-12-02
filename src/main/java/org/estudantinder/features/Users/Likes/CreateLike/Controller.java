package org.estudantinder.features.Users.Likes.CreateLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createStudentUseCase;

    public Response handle(JsonWebToken jwt, Long receiverId) throws Exception {
        try {
            createStudentUseCase.execute(jwt, receiverId);

            return Response
                .status(Response.Status.CREATED)
                .build();

        } catch(NotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Like";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        } catch(EntityExistsException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Like";

            return Response
                .status(Response.Status.CONFLICT)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Like";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
