package org.estudantinder.features.School.UpdateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public  class Controller {
    
    @Inject
    Feature updateSchool;

    public Response handle(Long id, DTO data)  {
        try {
            updateSchool.execute(id, data);

            return Response
                .status(Response.Status.OK)
                .entity(data)
                .build();

        } catch (EntityNotFoundException error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't update School";

            return Response
                .status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
        }
    }
}
