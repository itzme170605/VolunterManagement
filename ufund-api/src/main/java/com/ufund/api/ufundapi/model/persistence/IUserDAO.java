package com.ufund.api.ufundapi.model.persistence;
import java.io.IOException;
import com.ufund.api.ufundapi.model.User;


public interface IUserDAO {


        /**
         * Retrieves all Users.
         *
         * @return An array of all Users.
         * @throws IOException If an error occurs during retrieval.
         */
        User[] getUsers()  throws IOException;

        /**
         * Retrieves a User by its ID.
         *
         * @param userName The name of the user.
         * @return The User with the given name, or null if not found.
         * @throws IOException If an error occurs during retrieval.
         */
        User getUser(String userName) throws IOException;

        /**
         * Updates an exsisting User.
         *
         * @param user The User to update.
         * @return The created User, or null if a User with the same name exists.
         * @throws IOException If an error occurs during creation.
         */
        User updateUser(User user) throws IOException;

        /**
         * Creates a new User.
         *
         * @param need The User to update.
         * @return The created User, or null if a User with the same name exists.
         * @throws IOException If an error occurs during creation.
         */
        User createUser(User user) throws IOException;

        /**
         * Deletes a User by its userName.
         *
         * @param name The name of the User to delete.
         * @return True if the User was deleted, false if not found.
         * @throws IOException If an error occurs during deletion.
         */
        boolean deleteUser(String user) throws IOException;


}
