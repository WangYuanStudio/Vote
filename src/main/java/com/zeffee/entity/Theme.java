package com.zeffee.entity;


import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by zeffee on 2017/6/2.
 */
@Entity
@DynamicUpdate
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @Column
    @NotNull(message = "title is not null!")
    @Size(min = 1, max = 50, message = "size of title needs between 1 and 50")
    private String title;

    @Column
    @Size(max = 200, message = "size of description needs between 0 and 200")
    private String description;

    @Column(name = "start_time")
    @NotNull(message = "startTime is not null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start_time;

    @Column(name = "end_time")
    @NotNull(message = "endTime is not null!")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date end_time;

    @Column(insertable = false, updatable = false)
    private int counts;

    @Column(name = "votes_per_user")
    @NotNull(message = "votes_per_user is not null!")
    @Min(value = 1, message = "votes_per_user needs larger than 1")
    @Max(value = 255, message = "votes_per_user needs larger than 255")
    private int votes_per_user;

    @Column(name = "uid")
    private String uid;

    @Column(name = "anonymous")
    @Min(value = 0, message = "anonymous need between 0 and 1")
    @Max(value = 1, message = "anonymous need between 0 and 1")
    private int anonymous;

    @Column(name = "oid_list", insertable = false)
    private String oid_list;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tid")
    private Set<Options> options;


    public Set<Options> getOptions() {
        return options;
    }

    public Theme setOptions(Set<Options> options) {
        this.options = options;
        return this;
    }

    public String getUid() {
        return uid;
    }

    public Theme setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public Theme() {
    }

    public Theme(String title, Date start_time, Date end_time, int votes_per_user) {
        this.title = title;
        this.start_time = start_time;
        this.end_time = end_time;
        this.votes_per_user = votes_per_user;
    }

    public int getTid() {
        return tid;
    }

    public Theme setTid(int tid) {
        this.tid = tid;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Theme setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getStart_time() {
        return start_time;
    }

    public Theme setStart_time(Date start_time) {
        this.start_time = start_time;
        return this;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public Theme setEnd_time(Date end_time) {
        this.end_time = end_time;
        return this;
    }

    public int getCounts() {
        return counts;
    }

    public Theme setCounts(int counts) {
        this.counts = counts;
        return this;
    }

    public int getVotes_per_user() {
        return votes_per_user;
    }

    public Theme setVotes_per_user(int votes_per_user) {
        this.votes_per_user = votes_per_user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }

    public String getOid_list() {
        return oid_list;
    }

    public void setOid_list(String oid_list) {
        this.oid_list = oid_list;
    }
}
