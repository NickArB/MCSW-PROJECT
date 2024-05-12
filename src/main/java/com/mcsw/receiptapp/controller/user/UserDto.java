package com.mcsw.receiptapp.controller.user;

import com.mcsw.receiptapp.model.RoleEnum;

public class UserDto{
    String name;
    String lastName;
    String email;
    String password;
    String role;

    public UserDto(){
        role = RoleEnum.USER.getValue();
    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto [name=" + name + ", lastName=" + lastName + ", email=" + email + ", password=" + password
                + ", role=" + role + "]";
    }
}
