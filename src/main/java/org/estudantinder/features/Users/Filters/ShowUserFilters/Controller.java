package org.estudantinder.features.Users.Filters.ShowUserFilters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Preferences;
import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showUserFilters;

    public Response handle(JsonWebToken jwt) throws Exception {
        try {
            Preferences userPreferences = showUserFilters.execute(jwt);

            return Response.status(Response.Status.OK).entity(userPreferences).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel mostrar as preferencias do usuário", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar as preferencias do usuário", error);

        }
    }
}
