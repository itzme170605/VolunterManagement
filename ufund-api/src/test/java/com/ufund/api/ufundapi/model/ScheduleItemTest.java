package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Unit test suite for the ScheduleItem class
 */
@Tag("Model-tier")
public class ScheduleItemTest {

    @Test
    public void testConstructorAndGetters() {
        // Setup
        Need need = new Need(101, "Food Drive", "Community Center", "Distribute food", "011224 120000", 10, true);
        Date dateTime = new Date();

        // Invoke
        ScheduleItem scheduleItem = new ScheduleItem(need, dateTime);

        // Analyze
        assertEquals(need, scheduleItem.getNeed());
        assertEquals(dateTime, scheduleItem.getDateTime());
    }

    @Test
    public void testSetters() {
        // Setup
        Need initialNeed = new Need(202, "Beach Cleanup", "Local Beach", "Clean the beach", "021224 080000", 15, true);
        Date initialDateTime = new Date();

        ScheduleItem scheduleItem = new ScheduleItem(initialNeed, initialDateTime);

        // Updated data
        Need updatedNeed = new Need(303, "Tree Planting", "Park", "Plant trees", "031224 100000", 20, false);
        Date updatedDateTime = new Date(System.currentTimeMillis() + 3600000); // 1 hour later

        // Invoke
        scheduleItem.setNeed(updatedNeed);
        scheduleItem.setDateTime(updatedDateTime);

        // Analyze
        assertEquals(updatedNeed, scheduleItem.getNeed());
        assertEquals(updatedDateTime, scheduleItem.getDateTime());
    }

    @Test
    public void testToString() {
        // Setup
        Need need = new Need(404, "Workshop", "Library", "Host a workshop", "041224 140000", 5, true);
        Date dateTime = new Date();

        ScheduleItem scheduleItem = new ScheduleItem(need, dateTime);

        String expectedString = "ScheduleItem{need=" + need.toString() + ", dateTime=" + dateTime.toString() + "}";

        // Analyze
        assertEquals(expectedString, scheduleItem.toString());
    }
}
