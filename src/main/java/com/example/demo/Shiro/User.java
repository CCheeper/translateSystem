package com.example.demo.Shiro;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User {
    @Id
    private String id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String roles;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User(String id, String username, String password, String roles) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
