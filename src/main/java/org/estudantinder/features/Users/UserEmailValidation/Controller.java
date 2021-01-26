package org.estudantinder.features.Users.UserEmailValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature validateEmailUseCase;

    public Response handle(DTO data) throws Exception {
        try {
            validateEmailUseCase.execute(data);

            return Response
                .status(Response.Status.NO_CONTENT)
                .build();

        } catch (EntityExistsException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Email Already In Use";

            return Response
                .status(Response.Status.CONFLICT)
                .entity(errorMessage)
                .build();
            
        }catch(NullPointerException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = "No Data";
            errorMessage.message = "Couldn't validate email";

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
                
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't validate email";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
