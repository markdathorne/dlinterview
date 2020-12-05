package com.directline.dlinterview.dao;

import com.directline.dlinterview.model.User;

public interface UserDAO {

    User getUser(String email);

    User postUser(User user);

    User deleteUser(String email);
}
