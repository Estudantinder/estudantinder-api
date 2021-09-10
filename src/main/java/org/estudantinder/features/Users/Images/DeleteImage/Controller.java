package org.estudantinder.features.Users.Images.DeleteImage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteImage;

    public Response handle(JsonWebToken jwt, Integer imageIndex) throws Exception {
        try {
            deleteImage.execute(jwt, imageIndex);

            return Response.status(Response.Status.OK).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel deletar a imagem", error);

        } catch (BadRequestException error) {
            return ErrorResponse.handle(400, "Não foi possivel deletar a imagem", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Não foi possivel deletar a imagem", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel deletar a imagem", error);

        }
    }
}
