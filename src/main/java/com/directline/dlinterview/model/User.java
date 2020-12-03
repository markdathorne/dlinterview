package com.directline.dlinterview.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class User {
  // unique id (key) is email
  private String email;
  private String fullName;
  private String password;
  private String phoneNumber;
  private String dept;
  private String jobTitle;

  // construct new User
  public User(String email, String fullName, String password, String phoneNumber,
              String dept, String jobTitle) {
    this.email  = email;
    this.fullName = fullName;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.dept = dept;
    this.jobTitle = jobTitle;
  }

}