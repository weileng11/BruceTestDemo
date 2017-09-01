package com.bruce.core.database.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author:Bruce
 * Package:com.bruce.core.database.greendao.entity
 * time:2017/8/31.
 * contact：weileng143@163.com
 *
 * @description
 */
@Entity
public class User {
    @Id
    private Long id; //主键

    private String name; //名字

    @Generated(hash = 873297011)
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
