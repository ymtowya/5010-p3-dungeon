package model.player;

import java.util.HashMap;
import java.util.Map;

import model.Treasure;
import model.TreasureHolder;

public class DungeonPlayer implements TreasureHolder, Player {
  
  private int id;
  private int row;
  private int col;
  private Map<Treasure, Integer> treasures;
  
  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getCol() {
    return this.col;
  }

  @Override
  public void setRow(int newRow) {
    this.row = newRow;
  }

  @Override
  public void setCol(int newCol) {
    this.col = newCol;
  }

  @Override
  public void setTreasureMap(Map<Treasure, Integer> newTreasures) {
    this.treasures = new HashMap<>();
    this.treasures.putAll(newTreasures);
  }

  @Override
  public Map<Treasure, Integer> getTreasures() {
    Map<Treasure, Integer> resMap = new HashMap<>();
    resMap.putAll(this.treasures);
    return resMap;
  }

  @Override
  public int getTreasureCount(Treasure treasureName) {
    if (this.treasures.containsKey(treasureName)) {
      return this.treasures.get(treasureName);
    }
    return 0;
  }

  @Override
  public int getTotalTreasure() {
    int count = 0;
    for (int tmp : this.treasures.values()) {
      count += tmp;
    }
    return count;
  }

  @Override
  public boolean canHoldTreasureNow() {
    return true;
  }

}
