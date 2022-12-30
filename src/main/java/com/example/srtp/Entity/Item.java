package com.example.srtp.Entity;

import lombok.Data;

@Data
public class Item {

  private long itemId;
  private long marketId;
  private String itemName;
  private long itemCount;


  public long getItemId() {
    return itemId;
  }

  public void setItemId(long itemId) {
    this.itemId = itemId;
  }


  public long getMarketId() {
    return marketId;
  }

  public void setMarketId(long marketId) {
    this.marketId = marketId;
  }


  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }


  public long getItemCount() {
    return itemCount;
  }

  public void setItemCount(long itemCount) {
    this.itemCount = itemCount;
  }

}
