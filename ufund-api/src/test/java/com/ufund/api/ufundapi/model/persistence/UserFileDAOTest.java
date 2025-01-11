package com.ufund.api.ufundapi.model.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufund.api.ufundapi.model.Manager;
import com.ufund.api.ufundapi.model.Need;
import com.ufund.api.ufundapi.model.User;
import com.ufund.api.ufundapi.model.Helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Tag("Persistence-tier")
@ExtendWith(MockitoExtension.class)
public class UserFileDAOTest {

    @Mock
    UserFileDAO UserDAOTest;
    @Mock
    ArrayList <User> testUsers;

    @BeforeEach
    public void init_test() throws IOException {

        testUsers = new ArrayList<>();

        testUsers.add(new Helper("RawrX3", "12345"));
        testUsers.add(new Helper("TwT:3", "password"));
        testUsers.add(new Helper("RawrX3", "diffPW"));
        testUsers.add(new Manager("Admin", "Admin"));

        UserDAOTest = new UserFileDAO("test_users.json", new ObjectMapper());
    }

    @Test
    void test_Create() throws IOException {
        User u1 = UserDAOTest.createUser(testUsers.get(3));
        User u2 = UserDAOTest.createUser(testUsers.get(0));

        assertTrue(u1.isManager());
        assertFalse(u2.isManager());
    }

    @Test
    void test_Get() throws IOException {
        UserDAOTest.createUser(testUsers.get(3));
        UserDAOTest.createUser(testUsers.get(0));
        
        assertTrue(UserDAOTest.getUser("Admin").isManager());
        assertFalse(UserDAOTest.getUser("RawrX3").isManager());
        assertNull(UserDAOTest.getUser("User1"));
    }

    @Test
    void test_Update() throws IOException {
        UserDAOTest.updateUser(testUsers.get(2));
        assertEquals(UserDAOTest.getUser("RawrX3").getPassword(), testUsers.get(2).getPassword());

        Need testNeed = new Need(1, "Yippe", "The Place", "Stuff doin' things", "123", 3, true);
        Helper H = (Helper) testUsers.get(2);
        H.getBasket().add(testNeed);
        UserDAOTest.updateUser(H);
        Helper J = (Helper) UserDAOTest.getUser("RawrX3");
        assertTrue(J.getBasket().contains(testNeed));
    }

    @Test
    void test_Delete() throws IOException {
        assertTrue(UserDAOTest.deleteUser("Admin"));
        assertNull(UserDAOTest.getUser("Admin"));
    }

    @Test 
    void test_GetAll() throws IOException {
        
        Boolean u1 = false, u2 = false, u3 = true;

        UserDAOTest.createUser(testUsers.get(1));
        User users[] = UserDAOTest.getUsers();
        
        for(User u : users)
        {
            if(u.getUserName().equals(testUsers.get(2).getUserName()))
            {
                u1 = true;
            } else if(u.getUserName().equals(testUsers.get(1).getUserName()))
            {
                u2 = true;
            } else if(u.getUserName().equals(testUsers.get(3).getUserName()))
            {
                u3 = false;
            }
        }

        assertTrue(u1);
        assertTrue(u2);
        assertTrue(u3);
    }

    @Test
    void test_GetUserNotFound() throws IOException {
        User user = UserDAOTest.getUser("NonExistentUser");
        

        assertNull(user, "User should be null when not found");
    }
    @Test
    void test_DeleteUserNotFound() throws IOException {

        boolean result = UserDAOTest.deleteUser("NonExistentUser");

        assertFalse(result, "Deletion should return false for a non-existing user");
    }
    @Test
    void test_UpdateUserNotFound() throws IOException {
        User updatedUser = new Helper("NonExistentUser", "newPassword");
        User result = UserDAOTest.updateUser(updatedUser);
        

        assertNull(result, "Update should return null for a non-existing user");
    }


    @AfterAll
    public static void close_tests() throws IOException {
        File f = new File("test_users.json");
        if(f.exists())
        {
            FileWriter w = new FileWriter(f);
            w.write("[]");
            w.close();
        }
    }
}
