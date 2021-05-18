package org.estudantinder.features.Subjects.UpdateSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.entities.Subject;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature updateSubject;

    public Response handle(Long id, DTO data) throws Exception {
        try {
            Subject updatedSubject = updateSubject.execute(id, data);

            return Response.status(Response.Status.OK).entity(updatedSubject).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't update Subject", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't update Subject", error);

        }
    }
}
