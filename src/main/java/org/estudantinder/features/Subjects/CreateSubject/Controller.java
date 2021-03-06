package org.estudantinder.features.Subjects.CreateSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createSubject;

    public Response handle(DTO data) throws Exception {
        try {
            createSubject.execute(data);

            return Response.status(Response.Status.CREATED).entity(data).build();

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Couldn't create Subject", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't create Subject", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't create Subject", error);

        }
    }
}