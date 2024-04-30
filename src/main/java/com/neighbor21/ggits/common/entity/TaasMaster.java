package com.neighbor21.ggits.common.entity;

import java.util.List;
import java.util.Map;

public class TaasMaster {
  List<TaasAcdntDstrctMaster> positions;
    Map<String, TaasAcdntDstrctMaster> sggGroup;

  public List<TaasAcdntDstrctMaster> getPositions() {
    return positions;
  }

  public void setPositions(List<TaasAcdntDstrctMaster> positions) {
    this.positions = positions;
  }

  public Map<String, TaasAcdntDstrctMaster> getSggGroup() {
    return sggGroup;
  }

  public void setSggGroup(Map<String, TaasAcdntDstrctMaster> sggGroup) {
    this.sggGroup = sggGroup;
  }
}
