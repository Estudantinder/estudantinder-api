package org.estudantinder.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Likes like;

    @OneToOne
    private Likes mutualLike;

    public Long getId() {
        return id;
    }

    public Likes getMutualLike() {
        return mutualLike;
    }

    public void setMutualLike(Likes mutualLike) {
        this.mutualLike = mutualLike;
    }

    public Likes getLike() {
        return like;
    }

    public void setLike(Likes like) {
        this.like = like;
    }

}
