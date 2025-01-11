package com.ufund.api.ufundapi.model;

import com.ufund.api.ufundapi.model.Manager;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.io.IOException;

import javax.security.auth.message.callback.PrivateKeyCallback;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Model-tier")
@ExtendWith(MockitoExtension.class)
public class ManagerTest {
    @Mock
    Manager TestManager;

    @Test
    public void testManager_Constructor()
    {
        //Setup Manager to test
        Manager TestManager = new Manager("Admin", "8675309");

        //Test the expected fields and methods
        assertEquals("Admin", TestManager.getUserName());
        assertEquals("8675309", TestManager.getPassword());
        assertTrue(TestManager.isManager());
    }
}
