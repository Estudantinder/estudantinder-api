package org.estudantinder.features.Students.Likes.ShowLikes;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.commom.ErrorResponse;
import org.estudantinder.features.commom.Student;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showLikes;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            List<Student> filteredStudent = showLikes.execute(jwt);

            return Response.status(Response.Status.OK).entity(filteredStudent).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar os likes", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar os likes", error);

        }
    }
}