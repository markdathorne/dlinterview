package com.directline.dlinterview.dao;

import com.directline.dlinterview.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserDAOImpl implements UserDAO {

    final static Logger logger = LogManager.getLogger(UserDAOImpl.class);
    private static ConcurrentHashMap<String, User> dbUsers = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, User> getDbUsers() {
//        dbUsers.values().stream()
//                .forEach(System.out::println);
        return dbUsers;
    }

    /**
     * get user for given email address
     * @param email email address
     * @return User object
     */
    public User getUser(String email) {
        if (dbUsers.containsKey(email)) {
            return dbUsers.get(email);
        } else {
            User user = new User("","","","","","");
            user.setMessage("User <" + email + "> not found");
            return user;
        }
    }

    /**
     * post user - add or replace
     * @param user object
     */
    public User postUser(User user) {
        String responseMessage;
        user.setLastUpdated(LocalDateTime.now());

// Here should be some validation
        if (!validateUser(user)) {
            return user;
        }

        if (!dbUsers.containsKey(user.getEmail())) {
            // new User
            logger.info("Posting new user:{}", user.toString());
            responseMessage = "New user added";
            user.setMessage(responseMessage);
            dbUsers.put(user.getEmail(), user);
        } else {
            // existing User?
            User oldValues = dbUsers.get(user.getEmail());
            logger.info("Replacing user:{}", dbUsers.get(user.getEmail()));
            responseMessage = "Updating user";
            user.setMessage(responseMessage);
            dbUsers.replace(user.getEmail(), oldValues, user);
            logger.info("Replaced user:{}", dbUsers.get(user.getEmail()));
        }
        return user;
    }

    /**
     * delete user given the email
     * @param email email address
     * @return user object
     */
    public User deleteUser(String email) {
        StringBuilder sbMsg = new StringBuilder();
        User user;
        if (dbUsers.containsKey(email)) {
            // user found - append deleted message
            user = dbUsers.get(email);
            dbUsers.remove(email);
            sbMsg.append(String.format("Deleted user with id %s", email));
        } else {
            // user not found - append failed to delete message
            user = new User("","","","","","");
            sbMsg.append(String.format("User with id %s not deleted", email));
        }
        // append generated message to user obj
        user.setMessage(sbMsg.toString());
        return user;
    }

    private boolean validateUser(User user) {
// phone number validation
// password validation
// Full name rules
// etc
        StringBuilder sbMessage = new StringBuilder();
        if (user.getEmail().length() == 0) {
            sbMessage.append("Email is empty - please provide");
            user.setMessage(sbMessage.toString());
            return false;
        }
        if (user.getPassword().length() < 7) {
            sbMessage.append("Password length must be > 6 chars");
            user.setMessage(sbMessage.toString());
            return false;
        }

        return true;
    }

}
