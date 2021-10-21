package org.estudantinder.features.Admins.Analytics;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class AnalyticsController {

    @Inject
    AnalyticsFeature analyticsFeature;

    public Response handle() throws Exception {
        try {
            AnalyticsDTO analyticsDTO = analyticsFeature.execute();

            return Response.status(Response.Status.OK).entity(analyticsDTO).build();
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel mostrar o numero de usuarios registrados", error);
        }
    }
}