package com.example.srtp.Entity;

import lombok.Data;

@Data
public class Market {

  private long marketId;
  private long commId;
  private String marketName;
  private String marketAccountName;
  private String marketAccountPass;
  private String marketAddress;
  private String marketPhoneNumber;


  public long getMarketId() {
    return marketId;
  }

  public void setMarketId(long marketId) {
    this.marketId = marketId;
  }


  public long getCommId() {
    return commId;
  }

  public void setCommId(long commId) {
    this.commId = commId;
  }


  public String getMarketName() {
    return marketName;
  }

  public void setMarketName(String marketName) {
    this.marketName = marketName;
  }


  public String getMarketAccountName() {
    return marketAccountName;
  }

  public void setMarketAccountName(String marketAccountName) {
    this.marketAccountName = marketAccountName;
  }


  public String getMarketAccountPass() {
    return marketAccountPass;
  }

  public void setMarketAccountPass(String marketAccountPass) {
    this.marketAccountPass = marketAccountPass;
  }


  public String getMarketAddress() {
    return marketAddress;
  }

  public void setMarketAddress(String marketAddress) {
    this.marketAddress = marketAddress;
  }


  public String getMarketPhoneNumber() {
    return marketPhoneNumber;
  }

  public void setMarketPhoneNumber(String marketPhoneNumber) {
    this.marketPhoneNumber = marketPhoneNumber;
  }

}
