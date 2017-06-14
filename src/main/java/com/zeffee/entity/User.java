package com.zeffee.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by zeffee on 2017/6/2.
 */
public class User {
    @Id
    private int uid;

    @Column
    @NotNull(message = "name is not null!")
    private String name;
}
