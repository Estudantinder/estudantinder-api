package org.estudantinder.features.Subjects.UpdateSubject;

import java.io.File;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class DTO {
    @FormParam("name")
    @PartType(MediaType.TEXT_PLAIN)
    public String name;

    @FormParam("photo")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public File photo;
}
