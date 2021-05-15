package org.estudantinder.features.Users.Filters.EditUserFilters;

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
    Feature updateUserPreferences;

    public Response handle(JsonWebToken jwt, DTO data) throws Exception {
        try {
            Preferences userPreferences = updateUserPreferences.execute(jwt, data);

            return Response.status(Response.Status.OK).entity(userPreferences).build();

        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Couldn't update user preferences", error);

        } catch (NullPointerException error) {
            return ErrorResponse.handle(400, "Couldn't update user preferences", error);

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't update user preferences", error);

        }
    }
}
