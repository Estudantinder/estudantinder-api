package org.estudantinder.features.Subjects.UpdateSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.entities.Subject;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public  class Controller {
    
    @Inject
    Feature updateSubject;

    public Response handle(Long id, DTO data) throws Exception {
        try {
            Subject updatedSubject = updateSubject.execute(id, data);

            return Response
                .status(Response.Status.OK)
                .entity(updatedSubject)
                .build();
        } catch (EntityNotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't update Subject";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create Subject";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
