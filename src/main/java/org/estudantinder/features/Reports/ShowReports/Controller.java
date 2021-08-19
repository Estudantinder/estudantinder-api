package org.estudantinder.features.Reports.ShowReports;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature showReports;

    public Response handle() throws Exception {
        try {
            List<ShowReportDTO> reportsList = showReports.execute();

            return Response.status(Response.Status.OK).entity(reportsList).build();
        }catch (Exception error) {
            return ErrorResponse.handle(500, "Couldn't show Reports", error);
        }
    }
}
