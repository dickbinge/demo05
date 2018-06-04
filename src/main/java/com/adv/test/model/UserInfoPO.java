package com.adv.test.model;

import javax.persistence.*;

/**
 * Created by xiao.bo on 2018/2/26.
 CREATE TABLE IF NOT EXISTS user_info
 (
 id serial NOT NULL,
 username text NOT NULL,
 password text NOT NULL,
 type integer NOT NULL,
 phone text NOT NULL,
 email text,
 CONSTRAINT user_info_pkey PRIMARY KEY (id),
 CONSTRAINT user_info_username_unq UNIQUE (username),
 CONSTRAINT user_info_phone_unq UNIQUE (phone)
 );
 */
@Entity
@Table(name="user_info")
public class UserInfoPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "type")
    private Integer type;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
