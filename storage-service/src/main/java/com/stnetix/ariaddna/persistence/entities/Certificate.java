package com.stnetix.ariaddna.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by alexkotov on 26.04.17.
 */
@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    private Boolean isActive;

    public Certificate() {
        isActive = false;
    }

    public Certificate(String uuid, Boolean isActive) {
        this.uuid = uuid;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
