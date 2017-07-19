package com.zeffee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by zeffee on 2017/6/2.
 */
@Entity
public class User {
    @Id
    private String uid;

    @Column
    @NotNull(message = "name is not null!")
    private String name;


    @Column
    @NotNull(message = "photo is not null!")
    private String photo;

    public User() {
    }

    public User(String uid, String name, String photo) {
        this.uid = uid;
        this.name = name;
        this.photo = photo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
