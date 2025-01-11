package com.ufund.api.ufundapi.viewmodel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.ufund.api.ufundapi.model.Helper;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.model.persistence.IUserDAO;

@Tag("Service-tier")
public class UserServiceTest {

    private UserService userService;
    private IUserDAO userDAO;

    @BeforeEach
    public void setUp() {
        userDAO = mock(IUserDAO.class);
        userService = new UserService(userDAO);
    }

    @Test
    public void testCreateUser_Success() throws IOException {
        // Arrange
        User user = new Helper(null, null);
        user.setUserName("john");
        user.setPassword("password123");

        when(userDAO.createUser(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertEquals(user, result);
        verify(userDAO, times(1)).createUser(user);
    }

    @Test
    public void testCreateUser_IOException() throws IOException {
        // Arrange
        User user = new Helper(null, null);
        user.setUserName("john");

        when(userDAO.createUser(user)).thenThrow(new IOException("IO Exception"));

        // Act & Assert
        assertThrows(IOException.class, () -> userService.createUser(user));
        verify(userDAO, times(1)).createUser(user);
    }

    @Test
    public void testGetUser_Success() throws IOException {
        // Arrange
        String userName = "john";
        User user = new Helper(userName, null);
        user.setUserName(userName);

        when(userDAO.getUser(userName)).thenReturn(user);

        // Act
        User result = userService.getUser(userName);

        // Assert
        assertEquals(user, result);
        verify(userDAO, times(1)).getUser(userName);
    }

    @Test
    public void testGetUser_NotFound() throws IOException {
        // Arrange
        String userName = "nonexistent";

        when(userDAO.getUser(userName)).thenReturn(null);

        // Act
        User result = userService.getUser(userName);

        // Assert
        assertNull(result);
        verify(userDAO, times(1)).getUser(userName);
    }

    @Test
    public void testGetUsers_Success() throws IOException {
        // Arrange
        User user1 = new Helper(null, null);
        user1.setUserName("john");

        User user2 = new Helper(null, null);
        user2.setUserName("jane");

        User[] users = { user1, user2 };

        when(userDAO.getUsers()).thenReturn(users);

        // Act
        User[] result = userService.getUsers();

        // Assert
        assertArrayEquals(users, result);
        verify(userDAO, times(1)).getUsers();
    }

    @Test
    public void testUpdateUser_UserExists() throws IOException {
        // Arrange
        String userName = "john";
        User existingUser = new Helper(userName, userName);
        existingUser.setUserName(userName);
        existingUser.setPassword("oldPassword");

        User updatedUser = new Helper(userName, userName);
        updatedUser.setPassword("newPassword");

        when(userDAO.getUser(userName)).thenReturn(existingUser);
        when(userDAO.updateUser(any(User.class))).thenReturn(updatedUser);

        // Act
        User result = userService.updateUser(userName, updatedUser);

        // Assert
        assertEquals(updatedUser, result);
        assertEquals(userName, result.getUserName());
        verify(userDAO, times(1)).getUser(userName);
        verify(userDAO, times(1)).updateUser(updatedUser);
    }

    @Test
    public void testUpdateUser_UserDoesNotExist() throws IOException {
        // Arrange
        String userName = "nonexistent";
        User updatedUser = new Helper(userName, userName);
        updatedUser.setPassword("newPassword");

        when(userDAO.getUser(userName)).thenReturn(null);

        // Act
        User result = userService.updateUser(userName, updatedUser);

        // Assert
        assertNull(result);
        verify(userDAO, times(1)).getUser(userName);
        verify(userDAO, never()).updateUser(any(User.class));
    }

    @Test
    public void testDeleteUser_Success() throws IOException {
        // Arrange
        String userName = "john";

        when(userDAO.deleteUser(userName)).thenReturn(true);

        // Act
        boolean result = userService.deleteUser(userName);

        // Assert
        assertTrue(result);
        verify(userDAO, times(1)).deleteUser(userName);
    }

    @Test
    public void testDeleteUser_Failure() throws IOException {
        // Arrange
        String userName = "nonexistent";

        when(userDAO.deleteUser(userName)).thenReturn(false);

        // Act
        boolean result = userService.deleteUser(userName);

        // Assert
        assertFalse(result);
        verify(userDAO, times(1)).deleteUser(userName);
    }

    @Test
    public void testLogin_Successful() throws IOException {
        // Arrange
        String userName = "john";
        String password = "password123";
        User user = new Helper(password, password);
        user.setUserName(userName);
        user.setPassword(password);

        when(userDAO.getUser(userName)).thenReturn(user);

        // Act
        User result = userService.login(userName, password);

        // Assert
        assertEquals(user, result);
        verify(userDAO, times(1)).getUser(userName);
    }

    @Test
    public void testLogin_WrongPassword() throws IOException {
        // Arrange
        String userName = "john";
        String password = "wrongPassword";
        User user = new Helper(password, password);
        user.setUserName(userName);
        user.setPassword("password123");

        when(userDAO.getUser(userName)).thenReturn(user);

        // Act
        User result = userService.login(userName, password);

        // Assert
        assertNull(result);
        verify(userDAO, times(1)).getUser(userName);
    }

    @Test
    public void testLogin_UserNotFound() throws IOException {
        // Arrange
        String userName = "nonexistent";
        String password = "password123";

        when(userDAO.getUser(userName)).thenReturn(null);

        // Act
        User result = userService.login(userName, password);

        // Assert
        assertNull(result);
        verify(userDAO, times(1)).getUser(userName);
    }
}