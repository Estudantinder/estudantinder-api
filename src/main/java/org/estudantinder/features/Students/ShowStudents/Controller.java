package org.estudantinder.features.Students.ShowStudents;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Students.common.Student;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showStudents;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            List<Student> filteredStudents = showStudents.execute(jwt);

            return Response
                .status(Response.Status.OK)
                .entity(filteredStudents)
                .build();
        } catch (EntityNotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't show Students";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't show Students";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}