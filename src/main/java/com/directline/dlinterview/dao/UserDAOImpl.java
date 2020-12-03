package com.directline.dlinterview.dao;

import com.directline.dlinterview.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserDAOImpl implements UserDAO {

    Logger logger = LogManager.getLogger(UserDAOImpl.class);
    ConcurrentHashMap<String, User> dbUsers = new ConcurrentHashMap<>();

    /**
     * get user for given email address
     * @param email
     * @return
     */
    public User getUser(String email) {
        if (dbUsers.keySet().contains(email)) {
            return dbUsers.get(email);
        }
        return null;
    }

    /**
     * post user - add or replace
     * @param user
     */
    public void postUser(User user) {
        if (!dbUsers.keySet().contains(user.getEmail())) {
            dbUsers.put(user.getEmail(), user);
        } else {
            dbUsers.replace(user.getEmail(), user);
        }
    }

    /**
     * delete user given the email
     * @param email
     * @return boolean success/not
     */
    public boolean deleteUser(String email) {
        if (dbUsers.keySet().contains(email)) {
            dbUsers.remove(email);
            return true;
        }
        return false;
    }

}
