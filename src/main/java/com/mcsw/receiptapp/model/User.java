package com.mcsw.receiptapp.model;

import com.mcsw.receiptapp.controller.user.UserDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User {
    String id;

    String name;

    String lastName;

    String email;

    String passwordHash;

    List<RoleEnum> roles;

    Date createdAt;

    public User() {
    }


    public User(UserDto userDto) {
        name = userDto.getName();
        lastName = userDto.getLastName();
        email = userDto.getEmail();
        createdAt = new Date();
        roles = new ArrayList<>(Collections.singleton(RoleEnum.USER));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<RoleEnum> getRoles() {
        return roles;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
    }

}

