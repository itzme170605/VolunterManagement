package com.ufund.api.ufundapi.model.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Need;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Tag("Persistence-tier")
public class NeedFileDAOTest {

    private NeedFileDAO needFileDAO;

    @BeforeEach
    public void setup() throws IOException {
        // Setup file and object mapper
        ObjectMapper objectMapper = new ObjectMapper();
        needFileDAO = new NeedFileDAO("test_needs.json", objectMapper);
    }
        @AfterAll
    public static void clean() throws IOException {
        // Clear the contents of test_needs.json file after all tests
        File file = new File("test_needs.json");
        if (file.exists()) {
            FileWriter writer = new FileWriter(file);
            writer.write("[]"); // Write an empty array to represent no needs
            writer.close();
        }
    }

    @Test
    public void testCreateNeed() throws Exception {
        // Setup
        Need newNeed = new Need(2020, "Need1", "Facility1", "Desc1", "10102023", 5,true);

        // Invoke
        Need createdNeed = needFileDAO.createNeed(newNeed);
        
        // Assert
        assertEquals(newNeed.getName(), createdNeed.getName());
        assertNotEquals(2020, createdNeed.getId()); // Ensure unique ID assigned
    }

    @Test
    public void testGetNeed() throws Exception {
        // Setup
        Need newNeed = needFileDAO.createNeed(new Need(0, "Need2", "Facility2", "Desc2", "11102023", 8,true));

        // Invoke
        Need retrievedNeed = needFileDAO.getNeed(newNeed.getId());

        // Assert
        assertEquals(newNeed.getId(), retrievedNeed.getId());
        assertEquals(newNeed.getName(), retrievedNeed.getName());
    }

    @Test
    public void testDeleteNeed() throws Exception {
        // Setup
        Need newNeed = needFileDAO.createNeed(new Need(0, "Need3", "Facility3", "Desc3", "12102023", 3,true));

        // Invoke
        boolean deleted = needFileDAO.deleteNeed(newNeed.getId());

        // Assert
        assertTrue(deleted);
        assertNull(needFileDAO.getNeed(newNeed.getId())); // Should be null after deletion
    }

    @Test
    public void testFindNeeds() throws Exception {
        // Setup
        needFileDAO.createNeed(new Need(0, "Test Need1", "Facility", "Description", "13102023", 6,true));
        needFileDAO.createNeed(new Need(0, "Another Need2", "Facility", "Description", "14102023", 4,true));

        // Invoke
        Need[] foundNeeds = needFileDAO.findNeeds("Test");

        // Assert
        assertEquals(1, foundNeeds.length);
        assertEquals("Test Need1", foundNeeds[0].getName());
    }

    @Test
    public void testGetAllNeeds() throws Exception {
        // Setup
        needFileDAO.createNeed(new Need(0, "Need1", "Facility1", "Desc1", "15102023", 5,true));
        needFileDAO.createNeed(new Need(0, "Need2", "Facility2", "Desc2", "16102023", 8,true));

        // Invoke
        Need[] allNeeds = needFileDAO.getNeeds();

        // Assert
        assertEquals(2, allNeeds.length);
    }

    @Test
    public void testUpdateNeed() throws Exception {
        // Setup
        Need createdNeed = needFileDAO.createNeed(new Need(0, "Old Need", "Old Facility", "Old Desc", "17102023", 10,true));
        createdNeed.setName("Updated Need");
        createdNeed.setFacility("Updated Facility");

        // Invoke
        Need updatedNeed = needFileDAO.updateNeed(createdNeed);

        // Assert
        assertEquals("Updated Need", updatedNeed.getName());
        assertEquals("Updated Facility", updatedNeed.getFacility());
    }
}
