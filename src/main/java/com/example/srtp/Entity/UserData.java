package com.example.srtp.Entity;

import lombok.Data;

@Data
public class UserData {

  private long userId;
  private String userName;
  private String userPass;
  private String userPhoneNumber;
  private long coins;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPass() {
    return userPass;
  }

  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }


  public String getUserPhoneNumber() {
    return userPhoneNumber;
  }

  public void setUserPhoneNumber(String userPhoneNumber) {
    this.userPhoneNumber = userPhoneNumber;
  }


  public long getCoins() {
    return coins;
  }

  public void setCoins(long coins) {
    this.coins = coins;
  }

}
