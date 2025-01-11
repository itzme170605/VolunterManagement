package com.ufund.api.ufundapi.viewmodel.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.persistence.NeedFileDAO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Tag("Service-tier")
public class NeedServiceTest {

    private NeedService needService;
    private NeedFileDAO needFileDAO;

    @BeforeEach
    public void setup() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        needFileDAO = new NeedFileDAO("test_needs.json", objectMapper);
        needService = new NeedService(needFileDAO);
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
    public void testGetNeed() throws Exception {
        // Setup
        Need newNeed = needFileDAO.createNeed(new Need(0, "Test Need", "Facility", "Description", "10102023", 10,true));

        // Invoke
        Need retrievedNeed = needService.getNeed(newNeed.getId());

        // Assert
        assertEquals(newNeed.getId(), retrievedNeed.getId());
        assertEquals(newNeed.getName(), retrievedNeed.getName());
    }

    @Test
    public void testGetAllNeeds() throws Exception {
        // Setup
        needFileDAO.createNeed(new Need(0, "Need1", "Facility1", "Desc1", "10102023", 5,true));
        needFileDAO.createNeed(new Need(0, "Need2", "Facility2", "Desc2", "11102023", 8,true));

        // Invoke
        Need[] allNeeds = needService.getAllNeeds();

        // Assert
        assertEquals(4, allNeeds.length);
    }

    @Test
    public void testSearchNeeds() throws Exception {
        // Setup
        needFileDAO.createNeed(new Need(0, "Searchable Need", "Facility1", "Desc1", "12102023", 5,true));
        needFileDAO.createNeed(new Need(0, "Non-Matching Need", "Facility2", "Desc2", "13102023", 8,true));

        // Invoke
        Need[] foundNeeds = needService.searchNeeds("Searchable");

        // Assert
        assertEquals(1, foundNeeds.length);
        assertEquals("Searchable Need", foundNeeds[0].getName());
    }

    @Test
    public void testCreateNeed() throws Exception {
        // Setup
        Need newNeed = new Need(0, "New Need", "Facility", "Description", "14102023", 10,true);

        // Invoke
        Need createdNeed = needService.createNeed(newNeed);

        // Assert
        assertNotNull(createdNeed);
        assertNotEquals(0, createdNeed.getId());
        assertEquals("New Need", createdNeed.getName());
    }

    @Test
    public void testCreateNeedWithDuplicateID() throws IOException {
        // Setup
        Need firstNeed = needService.createNeed(new Need(0, "First Need", "Facility", "Description", "15102023", 10,true));
        Need duplicateNeed = new Need(firstNeed.getId(), "Duplicate Need", "Facility", "Description", "16102023", 5,true);

        // Invoke
        Need createdNeed = needService.createNeed(duplicateNeed);

        // Assert
        assertNotNull(createdNeed);
        assertNotEquals(firstNeed.getId(), createdNeed.getId()); // IDs should be different
    }

    @Test
    public void testUpdateNeed() throws Exception {
        // Setup
        Need createdNeed = needService.createNeed(new Need(0, "Old Need", "Old Facility", "Old Desc", "17102023", 10,true));
        createdNeed.setName("Updated Need");

        // Invoke
        Need updatedNeed = needService.updateNeed(createdNeed);

        // Assert
        assertEquals("Updated Need", updatedNeed.getName());
    }

    @Test
    public void testUpdateNonExistentNeed() throws Exception {
        // Setup
        Need nonExistentNeed = new Need(9999, "Non-Existent Need", "Facility", "Desc", "18102023", 10,true);

        // Assert & Invoke
        assertThrows(IllegalArgumentException.class, () -> {
            needService.updateNeed(nonExistentNeed);
        });
    }

    @Test
    public void testDeleteNeed() throws Exception {
        // Setup
        Need newNeed = needService.createNeed(new Need(0, "Need to Delete", "Facility", "Desc", "19102023", 5,true));

        // Invoke
        boolean isDeleted = needService.deleteNeed(newNeed.getId());

        // Assert
        assertTrue(isDeleted);
        assertNull(needService.getNeed(newNeed.getId())); // Should return null after deletion
    }
}
