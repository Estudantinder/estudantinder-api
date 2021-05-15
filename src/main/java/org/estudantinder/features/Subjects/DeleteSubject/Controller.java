package org.estudantinder.features.Subjects.DeleteSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature deleteSubject;

    public Response handle(Long id) throws Exception {
        try {
            deleteSubject.execute(id);

            return Response.status(Response.Status.OK).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't delete Subject", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't delete Subject", error);

        }
    }
}
