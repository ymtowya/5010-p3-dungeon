package model.player;

import java.util.HashMap;
import java.util.Map;
import model.Treasure;
import model.graph.Coordinate;

/**
 * An implementation of the player in the dungeon.
 *
 *
 */
public class DungeonPlayer implements Player {
  
  private int row;
  private int col;
  private String name;
  private Map<Treasure, Integer> treasures;
  
  /**
   * Initialize the player.
   *
   * @param r row
   * @param c column
   * @param n name
   */
  public DungeonPlayer(int r, int c, String n) {
    this.row = r;
    this.col = c;
    this.name = n;
    this.treasures = new HashMap<>();
    for (Treasure t : Treasure.values()) {
      this.treasures.put(t, 0);
    }
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

  @Override
  public void addTreasures(Map<Treasure, Integer> newTreasures) {
    for (Treasure t : newTreasures.keySet()) {
      if (this.treasures.containsKey(t)) {
        final int tmp = treasures.get(t);
        treasures.put(t, tmp + newTreasures.get(t));
      } else {
        treasures.put(t, newTreasures.get(t));
      }
    }
  }

  @Override
  public Coordinate getCoord() {
    return new Coordinate(row, col);
  }
  
  @Override
  public String getName() {
    return this.name;
  }

}
