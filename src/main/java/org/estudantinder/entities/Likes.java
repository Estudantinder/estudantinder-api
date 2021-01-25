package org.estudantinder.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


// O nome da entidade é "Likes", pois "Like" é uma palavra reservada
@Entity
public class Likes {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Users sender;

    @ManyToOne
    private Users receiver;

    public Long getId() {
        return id;
    }

    public Users getReceiver() {
        return receiver;
    }

    public void setReceiver(Users receiver) {
        this.receiver = receiver;
    }

    public Users getSender() {
        return sender;
    }

    public void setSender(Users sender) {
        this.sender = sender;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
