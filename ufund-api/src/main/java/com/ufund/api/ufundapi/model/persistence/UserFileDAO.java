package com.ufund.api.ufundapi.model.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.User;

/**
 * This class implments the IUserDAO interface using a file
 */
@Component
public class UserFileDAO implements IUserDAO {
    private Map<String, User> users;
    private ObjectMapper objectMapper;
    private String filename;

    public UserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        this.users = new HashMap<>();
        loadUsers();
    }

    @Override
    public boolean deleteUser(String userName) throws IOException {
        synchronized (users) {
            if (users.containsKey(userName)) {
                users.remove(userName);
                saveUsers(); // Persist changes to the file
                return true; // Deletion successful
            } else {
                return false; // User with given ID not found
            }
        }
    }

    @Override
    public User[] getUsers() throws IOException {
        synchronized(users){
            ArrayList<User> userArrayList = new ArrayList<>();
            for(User user: users.values()) {
                userArrayList.add(user);
            }
            User[] userArray = new User[userArrayList.size()];
            userArrayList.toArray(userArray);
            return userArray;
        }
    }


    @Override
    public User getUser(String userName) throws IOException {
        synchronized(users) {  
            if(this.users.containsKey(userName)) {
                return users.get(userName);
            } else {
                return null;
            }
        }
    }


    @Override
    public User createUser(User user) throws IOException {
        synchronized (users) {
            users.put(user.getUserName(), user);
            saveUsers(); // May throw an IOException
            return user;
        }
    }


    @Override
    public User updateUser(User user) throws IOException {
        synchronized(users) {
            if(!users.containsKey(user.getUserName())) {
                return null;        //user doenst exists
            } else {
                users.put(user.getUserName(), user); //update the user
                return user;
            }
        }
    }

    private boolean loadUsers() throws IOException {
        File file = new File(filename);
        if (file.exists()) {
            User[] usersArray = objectMapper.readValue(file, User[].class);
            for (User user : usersArray) {
                users.put(user.getUserName(), user);    //map users to the username
            }
        } else {
            // If the file doesn't exist, initialize it
            saveUsers();
            return true;
        }
        return false;
    }
    
    private boolean saveUsers() throws IOException {
        User[] usersArray = users.values().toArray(new User[0]);
        objectMapper.writeValue(new File(filename), usersArray);
        return true;
    }

}
