package com.zeffee.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zeffee on 2017/6/2.
 */
@Entity
public class Votes implements Serializable {
    private static final long serialVersionUID = 2L;

    @EmbeddedId
    private VotesPrimaryKey primary;

    @Column
    @NotNull(message = "tid is not null!")
    private int tid;


    public Votes() {
    }

    public Votes(VotesPrimaryKey primary, int tid) {
        this.primary = primary;
        this.tid = tid;
    }

    public Votes(String uid, int oid, int tid) {
        this.primary = new VotesPrimaryKey(uid, oid);
        this.tid = tid;
    }

    public int getTid() {
        return tid;
    }

    public Votes setTid(int tid) {
        this.tid = tid;
        return this;
    }

    public String getUid() {
        return primary.getUid();
    }

    public void setUid(String uid) {
        primary.setUid(uid);
    }

    public int getOid() {
        return primary.getOid();
    }

    public Votes setOid(int oid) {
        primary.setOid(oid);
        return this;
    }
}
