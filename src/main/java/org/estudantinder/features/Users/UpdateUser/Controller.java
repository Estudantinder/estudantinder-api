package org.estudantinder.features.Users.UpdateUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Users.UpdateUser.DTO.UserDTO;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature updateUser;

    public Response handle(JsonWebToken jwt, UserDTO data) throws Exception {
        try {
            updateUser.execute(jwt, data);

            return Response
                .status(Response.Status.OK)
                .build();

        }catch (NotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't update User";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
            
        } catch(NullPointerException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = "No Data";
            errorMessage.message = "Couldn't update User";

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't update User";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
