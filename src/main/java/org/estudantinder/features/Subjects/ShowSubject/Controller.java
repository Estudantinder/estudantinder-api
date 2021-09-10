package org.estudantinder.features.Subjects.ShowSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.entities.Subject;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showSubject;

    public Response handle(Long id) throws Exception {
        try {
            Subject subject = showSubject.execute(id);

            return Response.status(Response.Status.OK).entity(subject).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar a materia", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar a materia", error);

        }
    }
}