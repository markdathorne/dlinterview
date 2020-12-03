package com.directline.dlinterview.controller;

import com.directline.dlinterview.dao.UserDAO;
import com.directline.dlinterview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="v1/directline/users")
public class UserController {

    private UserDAO userDAO;
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(value="/get/{email}")
    public ResponseEntity<User> getUser( @PathVariable("email") String email) {
        User user = userDAO.getUser(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value="/put/{email}/{fullName}/{password}/{phoneNumber}/{dept}/{jobTitle}")
    public ResponseEntity<User> postUser( @PathVariable("email") String email,
                                          @PathVariable("fullName") String fullName,
                                          @PathVariable("password") String password,
                                          @PathVariable("phoneNumber") String phoneNumber,
                                          @PathVariable("dept") String dept,
                                          @PathVariable("jobTitle") String jobTitle) {
        User user = new User(email,fullName,password,phoneNumber,dept,jobTitle);
        userDAO.postUser(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value="/delete/{email}")
    public ResponseEntity<String> deleteUser( @PathVariable("email") String email) {

        userDAO.deleteUser(email);
        return ResponseEntity.ok(email);
    }
}
