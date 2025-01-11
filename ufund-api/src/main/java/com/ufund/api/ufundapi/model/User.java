package com.ufund.api.ufundapi.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Helper.class, name = "helper"),
    @JsonSubTypes.Type(value = Manager.class, name = "admin")
})

public abstract class User {
    // Authentication fields
    private String userName;
    private String password;
    boolean manager;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        // Initialize other fields as necessary
    }

    public User() {}

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean authenticate(String passwordIn) {
        return passwordIn.equals(this.password);
    }

    public abstract boolean isManager();
}