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
    private Student sender;

    @ManyToOne
    private Student receiver;

    public Long getId() {
        return id;
    }

    public Student getReceiver() {
        return receiver;
    }

    public void setReceiver(Student receiver) {
        this.receiver = receiver;
    }

    public Student getSender() {
        return sender;
    }

    public void setSender(Student sender) {
        this.sender = sender;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
