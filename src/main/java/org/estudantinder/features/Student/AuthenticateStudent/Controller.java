package org.estudantinder.features.Student.AuthenticateStudent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorMessage;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createStudentUseCase;

    public Response handle(DTO data) throws Exception {
        try {
            String token = createStudentUseCase.execute(data);

            JsonObject tokenObject = Json.createObjectBuilder()
                .add("token", token)
                .build();

            return Response
                .status(Response.Status.OK)
                .entity(tokenObject)
                .build();

        } catch (NotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Student";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
            
        } catch (UnauthorizedException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Student";

            return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(errorMessage)
                .build();
            
        } catch(NullPointerException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = "No Data";
            errorMessage.message = "Couldn't create Student";

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Student";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
