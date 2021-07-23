package org.estudantinder.features.Statistics.ShowNumberOfUsers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showNumberOfUsers;

    public Response handle() throws Exception {
        try {
            NumberOfUsersDTO StudentMatchs = showNumberOfUsers.execute();

            return Response.status(Response.Status.OK).entity(StudentMatchs).build();

        } catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't show numberOfUsers", error);

        }
    }
}