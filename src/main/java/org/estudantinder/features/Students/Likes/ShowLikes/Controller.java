package org.estudantinder.features.Students.Likes.ShowLikes;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Students.common.Student;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showStudentLikes;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            List<Student> filteredStudent = showStudentLikes.execute(jwt);

            return Response
                .status(Response.Status.OK)
                .entity(filteredStudent)
                .build();
                
        } catch (NotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't show Student Likes";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't show Student Likes";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}