package com.ufund.api.ufundapi.viewmodel.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.viewmodel.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all users.
     *
     * @return A list of User objects.
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        try {
            User[] usersArray = userService.getUsers();
            List<User> usersList = Arrays.asList(usersArray);
            return ResponseEntity.ok(usersList);
        } catch (IOException e) {
            // Log the exception (not shown here for brevity)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves a user by username.
     *
     * @param userName The username of the user to retrieve.
     * @return The User object if found.
     */
    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable String userName) {
        try {
            User user = userService.getUser(userName);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IOException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Creates a new user.
     *
     * @param user The User object to create.
     * @return The created User object.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        LOG.info("POST /users");
        try {
            // Check if user already exists
            if (userService.getUser(user.getUserName()) != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (IOException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Updates an existing user.
     *
     * @param userName The username of the user to update.
     * @param user The updated User object.
     * @return The updated User object.
     */
    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        LOG.info("PUT /users/{username}");
        try {
            User updatedUser = userService.updateUser(user.getUserName(), user);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IOException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Deletes a user by username.
     *
     * @param userName The username of the user to delete.
     * @return No content if deletion was successful.
     */
    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {
        try {
            boolean deleted = userService.deleteUser(userName);
            if (deleted) {
                return ResponseEntity.noContent().build(); // 204 No Content
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (IOException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User loginUser) {
        try {
            User user = userService.login(loginUser.getUserName(), loginUser.getPassword());
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (IOException e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}