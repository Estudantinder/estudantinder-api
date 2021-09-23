package org.estudantinder.shared.exception;

import javax.ws.rs.WebApplicationException;

public class AppException extends WebApplicationException {
    
    private String message_ptBR;
    
    public AppException(final String message, final String message_ptBR, final int status) {
        super(message, status);
        setMessage_ptBR(message_ptBR);
    }


    public String getMessage_ptBR() {
        return message_ptBR;
    }

    public void setMessage_ptBR(String message_ptBR) {
        this.message_ptBR = message_ptBR;
    }
    
}
