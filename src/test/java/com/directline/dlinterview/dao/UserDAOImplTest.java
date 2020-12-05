package com.directline.dlinterview.dao;

import com.directline.dlinterview.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImplTest {

    Logger logger = LogManager.getLogger(UserDAOImplTest.class);

    UserDAOImpl userDAO = new UserDAOImpl();
    final String user1Email = "abc@email.com";
    final String user2Email = "bcd@email.com";
    final String user3Email = "cde@email.com";

    @Test
    void getUser() {
        User user = userDAO.getUser(user1Email);
        logger.info("User:{}", user.toString());
        /*
 user should be user1
        assertFalse(user.getEmail() == user1Email,
                "User '" + user.getEmail() + "' expected but '" + user2Email + "' found!");
*/
        assertSame(user.getEmail(), user1Email,
                "User '" + user.getEmail() + "' expected but '" + user1Email + "' found!");
    }

    @Test
    void updateUser() {
        int count = userDAO.getDbUsers().size();
        System.out.println("DB size:" + count);

        final String testDept = "Testing dept";
        final String testPhone = "0888 888888";

        User userOld = userDAO.getUser(user3Email);
        System.out.println("User3:" + userOld.toString());
        User userNew = new User(userOld.getEmail(), userOld.getFullName(), userOld.getPassword(),
                testPhone, testDept, userOld.getJobTitle());
        userNew = userDAO.postUser(userNew);
        System.out.println("User3:" + userNew.toString());

        User user = userDAO.getDbUsers().get(user3Email);
        System.out.println("User:" + user.toString());

        assertEquals(userDAO.getDbUsers().size(), count, "User count is not the same!");

        assertEquals(user.getDept(), testDept,
                "User '" + user3Email + "' dept '" + user.getDept()
                        + "' not updated to '" + testDept + "'!");
        assertEquals(user.getPhoneNumber(), testPhone,
                "User " + user3Email + "' phone number '" + user.getPhoneNumber()
                        + "' not updated to '" + testPhone + "'!");
    }

    @Test
    void deleteUser() {
        int count = userDAO.getDbUsers().size();
        userDAO.deleteUser(user2Email);
        assertEquals(userDAO.getDbUsers().size(),  count -1, "User count has reduced by 1");
        assertFalse(userDAO.getDbUsers().containsKey(user2Email),
                "Users still contains '" + user2Email +"'!");
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
        userDAO.getDbUsers().put(user1.getEmail(), user1);
        userDAO.getDbUsers().put(user2.getEmail(), user2);
        userDAO.getDbUsers().put(user3.getEmail(), user3);
    }

}