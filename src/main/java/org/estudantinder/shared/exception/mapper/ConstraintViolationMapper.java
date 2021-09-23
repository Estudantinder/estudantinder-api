package org.estudantinder.shared.exception.mapper;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.estudantinder.shared.exception.ErrorResponse;


@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        int code = 400;

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.code = code;

        if (exception.getMessage() != null)
            errorResponse.message = exception.getMessage();

        return Response.status(code).entity(errorResponse).build();
    }

}
