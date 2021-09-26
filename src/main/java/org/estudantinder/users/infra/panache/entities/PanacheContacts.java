package org.estudantinder.users.infra.panache.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.estudantinder.users.domain.models.Contacts;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "contacts")
public class PanacheContacts extends PanacheEntityBase {
    @Id
    public UUID id;

    public Long whatsapp;
    public String twitter;
    public String facebook;
    public String instagram;

    public Contacts toContacts() {
        Contacts contacts = new Contacts();

        contacts.id = this.id;
        contacts.whatsapp = this.whatsapp;
        contacts.twitter = this.twitter;
        contacts.facebook = this.facebook;
        contacts.instagram = this.instagram;

        return contacts;
    }
}
