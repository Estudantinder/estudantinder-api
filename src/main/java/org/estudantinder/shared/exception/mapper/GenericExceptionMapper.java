package org.estudantinder.shared.exception.mapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.estudantinder.shared.exception.ErrorResponse;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Response toResponse(Exception exception) {
        int code = 500;

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code = code;

        if (exception.getMessage() != null)
            errorResponse.message = exception.getMessage();

        return Response.status(code).entity(errorResponse).build();
    }

}
