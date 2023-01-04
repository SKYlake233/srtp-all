package com.example.srtp.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Item {

  @TableId
  private long itemId;
  private long marketId;
  private String itemName;
  private long itemCount;
  private long itemValue;
}
