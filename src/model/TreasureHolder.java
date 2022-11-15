package model;

import java.util.Map;

public interface TreasureHolder {
  boolean canHoldTreasureNow();
  void setTreasureMap(Map<Treasure, Integer> newTreasures);
  void addTreasures(Map<Treasure, Integer> newTreasures);
  Map<Treasure, Integer> getTreasures();
  int getTotalTreasure();
}
