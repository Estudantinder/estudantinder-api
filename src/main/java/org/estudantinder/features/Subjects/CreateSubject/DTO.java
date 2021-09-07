package org.estudantinder.features.Subjects.CreateSubject;

import java.io.File;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class DTO {
    @FormParam("name")
    @PartType(MediaType.TEXT_PLAIN)
    @NotNull
    public String name;

    @FormParam("photo")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    @NotNull
    public File photo;
}
