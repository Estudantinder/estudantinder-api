package org.estudantinder.shared.exception.mapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.estudantinder.shared.exception.AppException;


@Provider
public class AppExceptionMapper implements ExceptionMapper<AppException> {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public Response toResponse(AppException exception) {
            int code = exception.getResponse().getStatus();

            ObjectNode exceptionJson = objectMapper.createObjectNode();

            if (exception.getMessage() != null)
                exceptionJson.put("error_message", exception.getMessage());
            if (exception.getMessage_ptBR() != null)
                exceptionJson.put("error_message_ptBR", exception.getMessage());
            
            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
    }
    
}
