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

            return Response.status(Response.Status.CREATED).build();

        } catch (EntityExistsException error) {
            return ErrorResponse.handle(409, "Não foi possivel criar a materia", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel criar a materia", error);

        }
    }
}