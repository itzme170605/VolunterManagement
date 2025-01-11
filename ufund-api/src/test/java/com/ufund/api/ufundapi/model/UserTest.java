package com.ufund.api.ufundapi.model;

import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.viewmodel.service.INeedService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Model-tier")
@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    User testUser;

    @Test
    public void testUser_Constructor()
    {
        //Setup Manager to test
        testUser = new User("user1", "password") {
            @Override
            public boolean isManager() { return false; }
        };

        //Test the expected fields and methods
        assertEquals(testUser.getUserName(), "user1");
        assertEquals(testUser.getPassword(), "password");
    }

    @Test
    public void testUserName() {
        // Setup
        String userName = "coldfriesenjoyer";
        String password = "pasword123";
        testUser = new User(userName, password) {
            @Override
            public boolean isManager() { return false; }
        };

        String expected_userName = "Aguaral";

        // Invoke
        testUser.setUserName(expected_userName);

        // Analyze
        assertEquals(expected_userName, testUser.getUserName());
    }

    @Test
    public void testPassword() {
        // Setup
        String userName = "coldfriesenjoyer";
        String password = "pasword123";
        testUser = new User(userName, password) {
            @Override
            public boolean isManager() { return false; }
        };

        String expected_password = "thisisa100%securepassword";

        // Invoke
        testUser.setPassword(expected_password);

        // Analyze
        assertEquals(expected_password, testUser.getPassword());
    }

    @Test
    public void testFailAuthentication() {
        // Setup
        String userName = "coldfriesenjoyer";
        String password = "pasword123";
        testUser = new User(userName, password) {
            @Override
            public boolean isManager() { return false; }
        };

        boolean expected_authentication = false;

        // Invoke
        testUser.setPassword("not the original password!");

        // Analyze
        assertEquals(expected_authentication, testUser.authenticate(password));
    }

    @Test
    public void testPassAuthentication() {
        // Setup
        String userName = "coldfriesenjoyer";
        String password = "pasword123";
        testUser = new User(userName, password) {
            @Override
            public boolean isManager() { return false; }
        };

        boolean expected_authentication = true;

        // Analyze
        assertEquals(expected_authentication, testUser.authenticate(password));
    }
}
