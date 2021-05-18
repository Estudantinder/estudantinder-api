package org.estudantinder.features.Schools.ShowSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.entities.School;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showSchool;

    public Response handle(Long id) throws Exception {
        try {
            School school = showSchool.execute(id);

            return Response.status(Response.Status.OK).entity(school).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't show School", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't show School", error);

        }
    }
}