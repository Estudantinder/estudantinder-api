package org.estudantinder.features.Users.AuthenticateUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Users.AuthenticateUser.DTO.JwtDTO;
import org.estudantinder.features.Users.AuthenticateUser.DTO.LoginDTO;
import org.estudantinder.features.commom.ErrorMessage;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createUserUseCase;

    public Response handle(LoginDTO data) throws Exception {
        try {
            JwtDTO returnObject = createUserUseCase.execute(data);

            return Response
                .status(Response.Status.OK)
                .entity(returnObject)
                .build();

        } catch (NotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create User";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
            
        } catch (UnauthorizedException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create User";

            return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(errorMessage)
                .build();
            
        } catch(NullPointerException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = "No Data";
            errorMessage.message = "Couldn't create User";

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create User";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
