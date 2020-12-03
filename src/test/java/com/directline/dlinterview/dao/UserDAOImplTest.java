package com.directline.dlinterview.dao;

import com.directline.dlinterview.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

    UserDAOImpl userDAO = new UserDAOImpl();
    final String user1Email = "abc@email.com";
    final String user2Email = "bcd@email.com";
    final String user3Email = "cde@email.com";

    @Test
    void getUser() {
        User user = userDAO.getUser(user1Email);
        // user should not be null, should be found
//        assertNotEquals("User is null!", null, user);
        // user should be user1
        assertEquals("User is not user:"+user1Email, user.getEmail(), user1Email);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }


    @BeforeEach
    void setUp() {
        User user1 = new User(
                user1Email,
                "Abc Def",
                "test1p@ssword",
                "07777 777771",
                "IT",
                "Web programmer"
        );
        User user2 = new User(
                user2Email,
                "Bcd Efg",
                "test2p@ssword",
                "07777 77772",
                "Sales",
                "Sales Manager"
        );
        User user3 = new User(
                user3Email,
                "Cde Fgh",
                "test3p@ssword",
                "07777 77773",
                "Marketing",
                "Sales Manager"
        );
        userDAO.dbUsers.put(user1.getEmail(), user1);
        userDAO.dbUsers.put(user2.getEmail(), user2);
        userDAO.dbUsers.put(user3.getEmail(), user3);
    }

}