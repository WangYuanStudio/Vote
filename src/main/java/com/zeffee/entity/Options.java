package com.zeffee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by zeffee on 2017/6/2.
 */
@Entity
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    @Column
    @NotNull(message = "content is not null!")
    private String content;

    @Column(insertable = false, updatable = false)
    private int counts;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tid", referencedColumnName = "tid")
    @JsonIgnore
    private Theme theme;

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Options() {
    }

    public Options(String content) {
        this.content = content;
    }


    public int getOid() {
        return oid;
    }

    public Options setOid(int oid) {
        this.oid = oid;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Options setContent(String content) {
        this.content = content;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public Options setCounts(int counts) {
        this.counts = counts;
        return this;
    }
}