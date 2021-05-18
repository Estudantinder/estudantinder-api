package org.estudantinder.features.Students.ShowStudents;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.features.Students.common.Student;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showStudents;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            List<Student> filteredStudents = showStudents.execute(jwt);

            return Response.status(Response.Status.OK).entity(filteredStudents).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't show Students", error);

        } catch (Exception error) {
            System.out.println(error.getMessage());
            return ErrorResponse.handle(500, "Couldn't show Students", error);

        }
    }
}