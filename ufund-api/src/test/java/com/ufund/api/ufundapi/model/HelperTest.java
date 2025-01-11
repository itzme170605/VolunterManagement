package com.ufund.api.ufundapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class HelperTest {

    private Helper helperTest;
    private ArrayList<Need> testNeeds = new ArrayList<>();

    public HelperTest() {
        // Initialize test needs
        testNeeds.add(new Need(0, "Need 0", "Place 0", "Facility 0", "12345", 420, true));
        testNeeds.add(new Need(1, "Need 1", "Place 1", "Facility 1", "56789", 421, true));
        testNeeds.add(new Need(2, "Need 2", "Place 2", "Facility 2", "01234", 422, true));

        // Initialize helper instance
        helperTest = new Helper("HelperUser", "password");
    }

    @Test
    public void testAddNeedToBasket() {
        // Add needs to basket
        helperTest.addNeedToBasket(testNeeds.get(0));
        helperTest.addNeedToBasket(testNeeds.get(1));

        // Assert that the needs were added
        assertTrue(helperTest.getBasket().contains(testNeeds.get(0)));
        assertTrue(helperTest.getBasket().contains(testNeeds.get(1)));
        assertEquals(2, helperTest.getBasket().size());
    }

    @Test
    public void testRemoveNeedFromBasket() {
        // Add needs to basket
        helperTest.addNeedToBasket(testNeeds.get(0));
        helperTest.addNeedToBasket(testNeeds.get(1));

        // Remove a need
        helperTest.removeNeedFromBasket(testNeeds.get(0));

        // Assert that the need was removed
        assertFalse(helperTest.getBasket().contains(testNeeds.get(0)));
        assertTrue(helperTest.getBasket().contains(testNeeds.get(1)));
        assertEquals(1, helperTest.getBasket().size());
    }

    @Test
    public void testCheckout() {
        // Add needs to basket
        helperTest.addNeedToBasket(testNeeds.get(0));
        helperTest.addNeedToBasket(testNeeds.get(1));

        // Checkout
        helperTest.checkout();

        // Assert that the basket is empty and the schedule is updated
        assertEquals(0, helperTest.getBasket().size());
        assertEquals(2, helperTest.getSchedule().size());

        // Ensure schedule contains the checked-out needs
        assertTrue(helperTest.getSchedule().stream().anyMatch(item -> item.getNeed().equals(testNeeds.get(0))));
        assertTrue(helperTest.getSchedule().stream().anyMatch(item -> item.getNeed().equals(testNeeds.get(1))));
    }

    // @Test
    // public void testRefundNeed() {
    //     // Add needs to basket and checkout
    //     helperTest.addNeedToBasket(testNeeds.get(0));
    //     helperTest.checkout();

    //     // Refund a need
    //     helperTest.refundNeed(testNeeds.get(0));

    //     // Assert that the need is no longer in the schedule
    //     assertFalse(helperTest.getSchedule().stream().anyMatch(item -> item.getNeed().equals(testNeeds.get(0))));
    // }

    @Test
    public void testAddNeedToBasketWithDuplicate() {
        // Add the same need multiple times
        helperTest.addNeedToBasket(testNeeds.get(0));
        helperTest.addNeedToBasket(testNeeds.get(0));

        // Assert that duplicates are allowed
        assertEquals(2, helperTest.getBasket().size());
        assertTrue(helperTest.getBasket().contains(testNeeds.get(0)));
    }


    @Test
    public void testRemoveNeedFromEmptyBasket() {
        // Attempt to remove a need from an empty basket
        helperTest.removeNeedFromBasket(testNeeds.get(0));

        // Assert that no exception is thrown and the basket remains empty
        assertEquals(0, helperTest.getBasket().size());
    }

    @Test
    public void testCheckoutWithEmptyBasket() {
        // Checkout with an empty basket
        helperTest.checkout();

        // Assert that the schedule and basket remain empty
        assertEquals(0, helperTest.getBasket().size());
        assertEquals(0, helperTest.getSchedule().size());
    }
}
