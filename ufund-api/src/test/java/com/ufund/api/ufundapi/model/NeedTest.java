package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test suite for the Need class
 */
@Tag("Model-tier")
public class NeedTest {

    @Test
    public void testConstructorAndGetters() {
        // Setup
        int id = 101;
        String name = "Food Distribution";
        String facility = "Community Center";
        String description = "Help distribute food packages.";
        String datetime = "021224 100000";
        boolean active = true;
        int required = 50;

        // Invoke
        Need need = new Need(id, name, facility, description, datetime, required, active);

        // Analyze
        assertEquals(id, need.getId());
        assertEquals(name, need.getName());
        assertEquals(facility, need.getFacility());
        assertEquals(description, need.getDescription());
        assertEquals(datetime, need.getDatetime());
        assertEquals(active, need.isActive());
        assertEquals(required, need.getRequired());
    }

    @Test
    public void testSetters() {
        // Setup
        Need need = new Need();
        int id = 201;
        String name = "Clothing Drive";
        String facility = "Local School";
        String description = "Collect and organize clothes.";
        String datetime = "151224 090000";
        boolean active = false;
        int required = 20;

        // Invoke
        need.setId(id);
        need.setName(name);
        need.setFacility(facility);
        need.setDescription(description);
        need.setDatetime(datetime);
        need.setStatus(active);
        need.setRequired(required);

        // Analyze
        assertEquals(id, need.getId());
        assertEquals(name, need.getName());
        assertEquals(facility, need.getFacility());
        assertEquals(description, need.getDescription());
        assertEquals(datetime, need.getDatetime());
        assertEquals(active, need.isActive());
        assertEquals(required, need.getRequired());
    }

    @Test
    public void testAddAndRemoveVolunteers() {
        // Setup
        Need need = new Need(101, "Workshop", "Library", "Host a workshop", "011124 140000", 10, true);

        Helper alice = new Helper("Alice", "alicepwd");
        Helper bob = new Helper("Bob", "bobpwd");
        Manager charlie = new Manager("Charlie", "adminpwd");

        // Add volunteers
        need.addVolunteer(alice);
        need.addVolunteer(bob);
        need.addVolunteer(charlie);

        ArrayList<String> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add("Alice");
        expectedVolunteers.add("Bob");
        expectedVolunteers.add("Charlie");

        // Analyze after adding
        assertEquals(expectedVolunteers, need.getVolunteers());
        assertEquals(3, need.getvolunteerCount());

        // Remove a volunteer
        need.removeVolunteer("Bob");
        expectedVolunteers.remove("Bob");

        // Analyze after removing
        assertEquals(expectedVolunteers, need.getVolunteers());
        assertEquals(2, need.getvolunteerCount());
    }

    @Test
    public void testVolunteersToString() {
        // Setup
        Need need = new Need(202, "Gardening", "Park", "Plant trees", "101224 080000", 5, true);

        Helper dana = new Helper("Dana", "danapwd");
        Helper eric = new Helper("Eric", "ericpwd");

        need.addVolunteer(dana);
        need.addVolunteer(eric);

        // Analyze
        String expectedString = "Dana, Eric, ";
        assertEquals(expectedString, need.volunteersToString());
    }

    @Test
    public void testToString() {
        // Setup
        Helper dana = new Helper("Dana", "danapwd");
        Helper eric = new Helper("Eric", "ericpwd");

        Need need = new Need(303, "Clean-up Drive", "Beach", "Pick up litter", "111124 070000", 15, true);
        need.addVolunteer(dana);
        need.addVolunteer(eric);

        String expectedString = String.format(
            Need.STRING_FORMAT, 
            303, "Clean-up Drive", "Beach", "Pick up litter", "111124 070000", true, 15, 2, "Dana, Eric, "
        );

        // Analyze
        assertEquals(expectedString, need.toString());
    }
}
