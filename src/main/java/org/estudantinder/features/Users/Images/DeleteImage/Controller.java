package org.estudantinder.features.Users.Images.DeleteImage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteImage;

    public Response handle(JsonWebToken jwt, Integer imageIndex) throws Exception {
        try {
            deleteImage.execute(jwt, imageIndex);

            return Response
                .status(Response.Status.OK)
                .build();

        } catch (NotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't Delete Photo";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
            
        } catch(BadRequestException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't Delete Photo";

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
        } catch(NullPointerException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = "No Data";
            errorMessage.message = "Couldn't Delete Photo";

            return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't Delete Photo";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
