package com.zeffee.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Zeffee on 2017/8/4.
 */
@Embeddable
public class VotesPrimaryKey implements Serializable{
    private static final long serialVersionUID = 1L;

    @Column
    @NotNull(message = "uid is not null!")
    private String uid;

    @Column
    @NotNull(message = "oid is not null!")
    private int oid;

    public VotesPrimaryKey(String uid, int oid) {
        this.uid = uid;
        this.oid = oid;
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

    public void setOid(int oid) {
        this.oid = oid;
    }
}
