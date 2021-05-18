package org.estudantinder.features.Schools.DeleteSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteSchool;

    public Response handle(Long id) throws Exception {
        try {
            deleteSchool.execute(id);

            return Response.status(Response.Status.OK).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't delete School", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't delete School", error);
        }
    }
}
