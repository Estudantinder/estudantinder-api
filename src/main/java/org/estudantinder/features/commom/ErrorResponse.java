package org.estudantinder.features.commom;

import javax.ws.rs.core.Response;

public class ErrorResponse {
    public static Response handle(int responseCode, String message, Exception error) {
        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.error = error.getMessage();
        errorMessage.message = message;

        return Response.status(responseCode).entity(errorMessage).build();
    }
}
