package org.estudantinder.shared.utils;

import java.util.UUID;

import javax.ws.rs.WebApplicationException;

public class UUIDUtils {
    public static UUID convertFromString(String idString) {
        try { 
            return UUID.fromString(idString);
        } catch (Exception exception) {
            throw new WebApplicationException("Id não é valido como UUID", 400);
        }
    }    
}
