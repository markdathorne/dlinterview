package com.directline.dlinterview.dao;

import com.directline.dlinterview.model.User;

public interface UserDAO {

    public User getUser(String email);

    public void postUser(User user);

    public boolean deleteUser(String email);
}
