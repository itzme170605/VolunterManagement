package com.ufund.api.ufundapi.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Manager extends User {
    @JsonCreator
    public Manager(@JsonProperty("userName") String userName,@JsonProperty("password") String password) {
        super(userName, password);
        //TODO Auto-generated constructor stub
    }

    //TODO figure out what needs to go here

    @Override
    public boolean isManager() {
        return true;
    }
}
