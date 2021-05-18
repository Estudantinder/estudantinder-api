package org.estudantinder.features.Users.Images.ImageUpload;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature imageUpload;

    public Response handle(JsonWebToken jwt, DTO data) throws Exception {
        try {
            String[] modifiedUser = imageUpload.execute(jwt, data);

            return Response.status(Response.Status.OK).entity(modifiedUser).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't image upload", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't image upload", error);

        }
    }
}
