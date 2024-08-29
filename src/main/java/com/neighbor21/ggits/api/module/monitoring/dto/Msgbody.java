package com.neighbor21.ggits.api.module.monitoring.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Msgbody {
  @JsonProperty("itemList")
  List<Itemlist> itemlistList;

  public void setItemlistList(List<Itemlist> itemlistList) {
    this.itemlistList = itemlistList;
  }

  public List<Itemlist> getItemlistList() {
    if (itemlistList == null)
      itemlistList = new ArrayList<>();
    return itemlistList;
  }

}
