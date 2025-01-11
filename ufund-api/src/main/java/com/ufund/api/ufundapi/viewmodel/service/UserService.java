package com.ufund.api.ufundapi.viewmodel.service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.model.persistence.IUserDAO;

@Service
public class UserService implements IUserService {
    private final IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to create.
     * @return The created User object.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public User createUser(User user) throws IOException {
        return userDAO.createUser(user);
    }

    /**
     * Retrieves a user by username.
     *
     * @param userName The username of the user to retrieve.
     * @return The User object if found; otherwise, null.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public User getUser(String userName) throws IOException {
        return userDAO.getUser(userName);
    }

    /**
     * Retrieves all users.
     *
     * @return An array of User objects.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public User[] getUsers() throws IOException {
        return userDAO.getUsers();
    }

    /**
     * Updates an existing user.
     *
     * @param userName The username of the user to update.
     * @param user The updated user object.
     * @return The updated User object if successful; otherwise, null.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public User updateUser(String userName, User user) throws IOException {
        User existingUser = userDAO.getUser(userName);
        if (existingUser == null) {
            return null;
        }
        user.setUserName(userName); // Ensure the username remains consistent
        return userDAO.updateUser(user);
    }

    /**
     * Deletes a user by username.
     *
     * @param userName The username of the user to delete.
     * @return True if deletion was successful; otherwise, false.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public boolean deleteUser(String userName) throws IOException {
        return userDAO.deleteUser(userName);
    }

    /** 
     * Logs a user in.
     * 
     * @param userName The username of the user to log in.
     * @param password The password of the user.
     * @return User if successful, otherwise return null
     * @throws IOException If an I/O error occurs.
    */
    @Override
    public User login(String userName, String password) throws IOException {
        User user = userDAO.getUser(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }
}