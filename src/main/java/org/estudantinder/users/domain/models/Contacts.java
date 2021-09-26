package org.estudantinder.users.domain.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Contacts {
    @JsonIgnore
    public UUID id;

    public Long whatsapp;
    public String twitter;
    public String facebook;
    public String instagram;
}
