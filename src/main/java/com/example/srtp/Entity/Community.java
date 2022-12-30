package com.example.srtp.Entity;

import lombok.Data;

@Data
public class Community {

  private long commId;
  private String commName;
  private String accountName;
  private String accountPass;
  private String address;
  private String phoneNumber;


  public long getCommId() {
    return commId;
  }

  public void setCommId(long commId) {
    this.commId = commId;
  }


  public String getCommName() {
    return commName;
  }

  public void setCommName(String commName) {
    this.commName = commName;
  }


  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }


  public String getAccountPass() {
    return accountPass;
  }

  public void setAccountPass(String accountPass) {
    this.accountPass = accountPass;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

}
