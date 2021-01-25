package org.estudantinder.features.Schools.UpdateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Schools.UpdateSchool.DTO.SchoolDTO;
import org.estudantinder.features.commom.ErrorMessage;

@ApplicationScoped
public  class Controller {
    
    @Inject
    Feature updateSchool;

    public Response handle(Long id, SchoolDTO data) throws Exception {
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
        } catch(Exception error) {
            ErrorMessage errorMessage = new ErrorMessage();
            
            errorMessage.error = error.getMessage();
            errorMessage.message = "Couldn't create School";

            return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(errorMessage)
                .build();
        }
    }
}
