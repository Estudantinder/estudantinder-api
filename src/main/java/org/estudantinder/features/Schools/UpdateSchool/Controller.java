package org.estudantinder.features.Schools.UpdateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.Schools.UpdateSchool.DTO.SchoolDTO;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature updateSchool;

    public Response handle(Long id, SchoolDTO data) throws Exception {
        try {
            updateSchool.execute(id, data);

            return Response.status(Response.Status.OK).entity(data).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel atualizar a escola", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel atualizar a escola", error);

        }
    }
}
