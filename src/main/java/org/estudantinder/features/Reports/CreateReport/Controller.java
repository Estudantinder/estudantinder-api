package org.estudantinder.features.Reports.CreateReport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.estudantinder.features.commom.ErrorResponse;

@ApplicationScoped
public class Controller {

    @Inject
    Feature createReport;

    public Response handle(CreateReportDTO reportData, Long reportedUserId) throws Exception {
        try {
            createReport.execute(reportData, reportedUserId);

            return Response.status(Response.Status.CREATED).build();
        } catch (NotFoundException error) {
            return ErrorResponse.handle(404, "Não foi possivel criar o report", error);
        } catch (Exception error) {
            return ErrorResponse.handle(500, "Não foi possivel criar o report", error);
        }
    }
}
