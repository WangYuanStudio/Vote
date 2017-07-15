package com.zeffee.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zeffee on 2017/6/2.
 */
@Entity
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vid;

    @Column
    @NotNull(message = "uid is not null!")
    private String uid;

    @Column
    @NotNull(message = "oid is not null!")
    private int oid;

    @Column
    @NotNull(message = "tid is not null!")
    private int tid;


    public int getVid() {
        return vid;
    }

    public Votes setVid(int vid) {
        this.vid = vid;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getOid() {
        return oid;
    }

    public Votes setOid(int oid) {
        this.oid = oid;
        return this;
    }

    public int getTid() {
        return tid;
    }

    public Votes setTid(int tid) {
        this.tid = tid;
        return this;
    }
}
