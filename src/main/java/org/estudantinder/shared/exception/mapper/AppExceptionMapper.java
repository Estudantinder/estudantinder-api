package org.estudantinder.shared.exception.mapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.estudantinder.shared.exception.AppException;

@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Response toResponse(AppException exception) {
        int code = exception.getResponse().getStatus();

        System.out.println(code);

        ErrorResponse errorResponse = new ErrorResponse();

        if (exception.getMessage() != null)
            errorResponse.message = exception.getMessage();
        if (exception.getMessage_ptBR() != null)
            errorResponse.message_ptBR = exception.getMessage_ptBR();

        return Response.status(code).entity(errorResponse).build();
    }

}
