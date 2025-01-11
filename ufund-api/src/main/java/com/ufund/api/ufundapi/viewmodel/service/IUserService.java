package com.ufund.api.ufundapi.viewmodel.service;

import java.io.IOException;

import com.ufund.api.ufundapi.model.User;

public interface IUserService {

    /**
     * Creates a new user.
     *
     * @param user The user object to create.
     * @return The created User object.
     * @throws IOException If an I/O error occurs.
     */
    User createUser(User user) throws IOException;

    /**
     * Retrieves a user by username.
     *
     * @param userName The username of the user to retrieve.
     * @return The User object if found; otherwise, null.
     * @throws IOException If an I/O error occurs.
     */
    User getUser(String userName) throws IOException;

    /**
     * Retrieves all users.
     *
     * @return An array of User objects.
     * @throws IOException If an I/O error occurs.
     */
    User[] getUsers() throws IOException;

    /**
     * Updates an existing user.
     *
     * @param userName The username of the user to update.
     * @param user The updated user object.
     * @return The updated User object if successful; otherwise, null.
     * @throws IOException If an I/O error occurs.
     */
    User updateUser(String userName, User user) throws IOException;

    /**
     * Deletes a user by username.
     *
     * @param userName The username of the user to delete.
     * @return True if deletion was successful; otherwise, false.
     * @throws IOException If an I/O error occurs.
     */
    boolean deleteUser(String userName) throws IOException;

    /**
     * Validates user credentials.
     *
     * @param userName The username to check.
     * @param password The password to validate.
     * @return The User object if credentials are valid; otherwise, null.
     * @throws IOException If an I/O error occurs.
     */
    User login(String userName, String password) throws IOException;
}