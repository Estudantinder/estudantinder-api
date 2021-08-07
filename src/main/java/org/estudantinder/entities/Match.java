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
    private Like like;

    @OneToOne
    private Like mutualLike;

    public Long getId() {
        return id;
    }

    public Like getMutualLike() {
        return mutualLike;
    }

    public void setMutualLike(Like mutualLike) {
        this.mutualLike = mutualLike;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

}
