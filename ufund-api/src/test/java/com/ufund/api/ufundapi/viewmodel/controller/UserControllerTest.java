package com.ufund.api.ufundapi.viewmodel.controller;

import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.viewmodel.service.UserService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Tag("Controller-tier")
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetUsers_Success() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        User[] usersArray = new User[]{testUser};
        when(userService.getUsers()).thenReturn(usersArray);

        // Act
        ResponseEntity<List<User>> response = userController.getUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Arrays.asList(usersArray), response.getBody());
    }

    @Test
    public void testGetUsers_InternalServerError() throws IOException {
        // Arrange
        when(userService.getUsers()).thenThrow(new IOException("IO Exception"));

        // Act
        ResponseEntity<List<User>> response = userController.getUsers();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetUser_Found() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        when(userService.getUser("testuser")).thenReturn(testUser);

        // Act
        ResponseEntity<User> response = userController.getUser("testuser");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testGetUser_NotFound() throws IOException {
        // Arrange
        when(userService.getUser("unknownuser")).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.getUser("unknownuser");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetUser_InternalServerError() throws IOException {
        // Arrange
        when(userService.getUser("testuser")).thenThrow(new IOException("IO Exception"));

        // Act
        ResponseEntity<User> response = userController.getUser("testuser");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateUser_Success() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        when(testUser.getUserName()).thenReturn("testuser");
        when(userService.getUser("testuser")).thenReturn(null);
        when(userService.createUser(any(User.class))).thenReturn(testUser);

        // Act
        ResponseEntity<User> response = userController.createUser(testUser);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testCreateUser_Conflict() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        when(testUser.getUserName()).thenReturn("testuser");
        when(userService.getUser("testuser")).thenReturn(testUser);

        // Act
        ResponseEntity<User> response = userController.createUser(testUser);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateUser_InternalServerError() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        when(testUser.getUserName()).thenReturn("testuser");
        when(userService.getUser("testuser")).thenReturn(null);
        when(userService.createUser(any(User.class))).thenThrow(new IOException("IO Exception"));

        // Act
        ResponseEntity<User> response = userController.createUser(testUser);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

@Test
void testUpdateUser_Success() throws IOException {
    // Setup
    User mockedUser = mock(User.class);
    User updatedUser = mock(User.class); // Expected updated user
    when(mockedUser.getUserName()).thenReturn("testUser");
    when(userService.updateUser(eq("testUser"), eq(mockedUser))).thenReturn(updatedUser);

    // Invoke
    ResponseEntity<User> response = userController.updateUser(mockedUser);

    // Analyze
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedUser, response.getBody());
}


    // @Test
    // public void testUpdateUser_NotFound() throws IOException {
    //     // Arrange
    //     User testUser = mock(User.class);
    //     when(userService.updateUser(eq("unknownuser"), any(User.class))).thenReturn(null);

    //     // Act
    //     ResponseEntity<User> response = userController.updateUser(testUser);

    //     // Assert
    //     assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    //     assertNull(response.getBody());
    // }

    // @Test
    // public void testUpdateUser_InternalServerError() throws IOException {
    //     // Arrange
    //     User testUser = mock(User.class);
    //     when(userService.updateUser(eq("testuser"), any(User.class))).thenThrow(new IOException("IO Exception"));

    //     // Act
    //     ResponseEntity<User> response = userController.updateUser(testUser);

    //     // Assert
    //     assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    //     assertNull(response.getBody());
    // }

    @Test
    public void testDeleteUser_Success() throws IOException {
        // Arrange
        when(userService.deleteUser("testuser")).thenReturn(true);

        // Act
        ResponseEntity<Void> response = userController.deleteUser("testuser");

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUser_NotFound() throws IOException {
        // Arrange
        when(userService.deleteUser("unknownuser")).thenReturn(false);

        // Act
        ResponseEntity<Void> response = userController.deleteUser("unknownuser");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUser_InternalServerError() throws IOException {
        // Arrange
        when(userService.deleteUser("testuser")).thenThrow(new IOException("IO Exception"));

        // Act
        ResponseEntity<Void> response = userController.deleteUser("testuser");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testLogin_Success() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        when(testUser.getUserName()).thenReturn("testuser");
        when(testUser.getPassword()).thenReturn("password");
        when(userService.login("testuser", "password")).thenReturn(testUser);

        // Act
        ResponseEntity<User> response = userController.login(testUser);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
    }

    @Test
    public void testLogin_Unauthorized() throws IOException {
        // Arrange
        User wrongUser = mock(User.class);
        when(wrongUser.getUserName()).thenReturn("testuser");
        when(wrongUser.getPassword()).thenReturn("wrongpassword");
        when(userService.login("testuser", "wrongpassword")).thenReturn(null);

        // Act
        ResponseEntity<User> response = userController.login(wrongUser);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testLogin_InternalServerError() throws IOException {
        // Arrange
        User testUser = mock(User.class);
        when(testUser.getUserName()).thenReturn("testuser");
        when(testUser.getPassword()).thenReturn("password");
        when(userService.login("testuser", "password")).thenThrow(new IOException("IO Exception"));

        // Act
        ResponseEntity<User> response = userController.login(testUser);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}