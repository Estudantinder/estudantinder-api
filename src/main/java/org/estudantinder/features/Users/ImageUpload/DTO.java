package org.estudantinder.features.Users.ImageUpload;

import java.io.InputStream;

import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class DTO {

    @NotNull(message = "profile photo must be inserted")
    @FormParam("photo0")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream photo0;

    @FormParam("photo1")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream photo1;

    @FormParam("photo2")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream photo2;

    @FormParam("photo3")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream photo3;
    
    @FormParam("photo4")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream photo4;

    @FormParam("photo5")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    public InputStream photo5;

}