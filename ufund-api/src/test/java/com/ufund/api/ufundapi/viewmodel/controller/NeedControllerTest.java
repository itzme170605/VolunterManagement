package com.ufund.api.ufundapi.viewmodel.controller;

import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.viewmodel.service.INeedService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("Controller-tier")
@ExtendWith(MockitoExtension.class)
public class NeedControllerTest {

    @Mock
    private INeedService needService;

    @InjectMocks
    private NeedController needController;

    @Test
    public void testGetNeed_Found() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(needService.getNeed(1)).thenReturn(testNeed);

        // Act
        ResponseEntity<Need> response = needController.getNeed(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testNeed, response.getBody());
    }

    @Test
    public void testGetNeed_NotFound() throws Exception {
        // Arrange
        when(needService.getNeed(1)).thenReturn(null);

        // Act
        ResponseEntity<Need> response = needController.getNeed(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetNeed_InternalServerError() throws Exception {
        // Arrange
        when(needService.getNeed(1)).thenThrow(new RuntimeException("Database error"));

        // Act
        ResponseEntity<Need> response = needController.getNeed(1);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(needService).getNeed(1);
    }

    @Test
    public void testGetNeeds_Success() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        Need[] needsArray = new Need[]{testNeed};
        when(needService.getAllNeeds()).thenReturn(needsArray);

        // Act
        ResponseEntity<?> response = needController.getNeeds();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(needsArray, (Need[]) response.getBody());
    }

    @Test
    public void testGetNeeds_InternalServerError() throws Exception {
        // Arrange
        when(needService.getAllNeeds()).thenThrow(new IOException("IO Error"));

        // Act
        ResponseEntity<?> response = needController.getNeeds();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody() instanceof String);
    }

    @Test
    public void testSearchNeeds_Found() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        Need[] needsArray = new Need[]{testNeed};
        when(needService.searchNeeds("Test")).thenReturn(needsArray);

        // Act
        ResponseEntity<Need[]> response = needController.searchNeeds("Test");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertArrayEquals(needsArray, response.getBody());
    }

    @Test
    public void testSearchNeeds_NotFound() throws Exception {
        // Arrange
        when(needService.searchNeeds("Test")).thenReturn(new Need[]{});

        // Act
        ResponseEntity<Need[]> response = needController.searchNeeds("Test");

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testSearchNeeds_InternalServerError() throws Exception {
        // Arrange
        when(needService.searchNeeds("Test")).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<Need[]> response = needController.searchNeeds("Test");

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteNeed_Success() throws Exception {
        // Arrange
        when(needService.deleteNeed(1)).thenReturn(true);

        // Act
        ResponseEntity<Need> response = needController.deleteNeed(1);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteNeed_NotFound() throws Exception {
        // Arrange
        when(needService.deleteNeed(1)).thenReturn(false);

        // Act
        ResponseEntity<Need> response = needController.deleteNeed(1);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteNeed_InternalServerError() throws Exception {
        // Arrange
        when(needService.deleteNeed(1)).thenThrow(new RuntimeException("Error"));

        // Act
        ResponseEntity<Need> response = needController.deleteNeed(1);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateNeed_Success() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(testNeed.getId()).thenReturn(1);

        // Since updateNeed calls getNeed internally
        Need existingNeed = mock(Need.class);
        when(needService.getNeed(1)).thenReturn(existingNeed);
        when(needService.updateNeed(any(Need.class))).thenReturn(testNeed);

        // Act
        ResponseEntity<Need> response = needController.updateNeed(testNeed);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testNeed, response.getBody());
    }

    @Test
    public void testUpdateNeed_NotFound() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(testNeed.getId()).thenReturn(1);
        when(needService.getNeed(1)).thenReturn(null);

        // Act
        ResponseEntity<Need> response = needController.updateNeed(testNeed);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateNeed_InternalServerError() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(testNeed.getId()).thenReturn(1);
        when(needService.getNeed(1)).thenReturn(testNeed);
        when(needService.updateNeed(any(Need.class))).thenThrow(new IOException("IO Error"));

        // Act
        ResponseEntity<Need> response = needController.updateNeed(testNeed);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateNeed_Success() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(testNeed.getId()).thenReturn(1);
        when(needService.getNeed(1)).thenReturn(null);
        when(needService.createNeed(any(Need.class))).thenReturn(testNeed);

        // Act
        ResponseEntity<Need> response = needController.createNeed(testNeed);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testNeed, response.getBody());
    }

    @Test
    public void testCreateNeed_Conflict() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(testNeed.getId()).thenReturn(1);
        when(needService.getNeed(1)).thenReturn(testNeed);

        // Act
        ResponseEntity<Need> response = needController.createNeed(testNeed);

        // Assert
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testCreateNeed_InternalServerError() throws Exception {
        // Arrange
        Need testNeed = mock(Need.class);
        when(testNeed.getId()).thenReturn(1);
        when(needService.getNeed(1)).thenReturn(null);
        when(needService.createNeed(any(Need.class))).thenThrow(new IOException("IO Error"));

        // Act
        ResponseEntity<Need> response = needController.createNeed(testNeed);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
    }
}