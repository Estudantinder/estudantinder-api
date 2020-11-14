package org.estudantinder.features.Student.CreateStudent;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.estudantinder.features.Student.CreateStudent.DTO.StudentDTO;

@Path("student")
@Tag(name = "Student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    @Inject
    Controller createStudentController;

    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Created")
    @APIResponse(responseCode = "400", description = "No Data Sent")
    @APIResponse(responseCode = "409", description = "Email Already In Use")
    @APIResponse(responseCode = "500", description = "Unexpected Error")
    public Response createStudent(StudentDTO data) throws Exception {
        return createStudentController.handle(data);
    }
}