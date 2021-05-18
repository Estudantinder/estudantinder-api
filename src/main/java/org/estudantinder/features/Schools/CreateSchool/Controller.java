package org.estudantinder.features.Schools.CreateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Schools.CreateSchool.DTO.SchoolDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createSchoolUseCase;

    public Response handle(SchoolDTO data) throws Exception {
        try {
            createSchoolUseCase.execute(data);

            return Response.status(Response.Status.CREATED).entity(data).build();

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Couldn't create School", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't create School", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't create School", error);

        }
    }
}
