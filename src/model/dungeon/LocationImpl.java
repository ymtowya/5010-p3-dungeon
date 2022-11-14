package model.dungeon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Direction;
import model.Treasure;
import model.TreasureHolder;

public class LocationImpl implements Location {
  
  private Set<Direction> directions;
  private Map<Treasure, Integer> treasures;
  
  public LocationImpl() {
    this.directions = new HashSet<>();
    this.treasures = new HashMap<>();
  }
  
  public LocationImpl(Set<Direction> newDirections,
      Map<Treasure, Integer> newTreasures) {
    this.directions = new HashSet<>();
    this.treasures = new HashMap<>();
    this.directions.addAll(newDirections);
    this.treasures.putAll(newTreasures);
  }

  @Override
  public boolean canWalk(Direction d) {
    return this.directions.contains(d);
  }

  @Override
  public void setDirectsSet(Set<Direction> newDirections) {
    this.directions = new HashSet<>(newDirections);
  }

  @Override
  public void addNewDirection(Direction d) {
    this.directions.add(d);
  }

  @Override
  public void removeDirection(Direction d) {
    this.directions.remove(d);
  }

  @Override
  public void clearAllDirections() {
    this.directions.clear();
  }

  @Override
  public void setTreasureMap(Map<Treasure, Integer> newTreasures) {
    if (isCave()) {
      this.treasures = new HashMap<>(newTreasures);
    }
  }

  @Override
  public Map<Treasure, Integer> getTreasures() {
    return new HashMap<>(this.treasures);
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
    return this.isCave();
  }

  @Override
  public Set<Direction> getAllDirections() {
    return new HashSet<Direction>(this.directions);
  }

  @Override
  public boolean isCave() {
    return this.directions.size() != 2;
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

}
