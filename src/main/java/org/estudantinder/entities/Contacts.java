package org.estudantinder.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contacts {
    @Id
    @GeneratedValue
    private Long id;

    private Long whatsapp;
    private String twitter;
    private String facebook;
    private String instagram;
    
    public Long getWhatsapp() {
        return whatsapp;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public void setWhatsapp(Long whatsapp) {
        this.whatsapp = whatsapp;
    }


}
