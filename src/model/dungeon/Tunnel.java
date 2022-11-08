package model.dungeon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Direction;
import model.Treasure;
import model.TreasureHolder;

public class Tunnel implements Location {

  Set<Direction> directions;
  
  public Tunnel() {
    this.directions = new HashSet<>();
  }
  
  public Tunnel(Direction e1, Direction e2) {
    this.directions = new HashSet<>();
    this.directions.add(e1);
    this.directions.add(e2);
  }
  
  @Override
  public void setTreasureMap(Map<Treasure, Integer> newTreasures) {
  }

  @Override
  public Map<Treasure, Integer> getTreasures() {
    return new HashMap<>();
  }

  @Override
  public int getTreasureCount(Treasure treasureName) {
    return 0;
  }

  @Override
  public int getTotalTreasure() {
    return 0;
  }

  @Override
  public boolean canWalk(Direction d) {
    return this.directions.contains(d);
  }

  @Override
  public void setDirectsSet(Set<Direction> newDirections) {
    this.directions = new HashSet<>();
    this.directions.addAll(newDirections);
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
  public boolean canHoldTreasureNow() {
    return false;
  }

  @Override
  public Set<Direction> getAllDirections() {
    return new HashSet<>(this.directions);
  }

  @Override
  public boolean isTunnel() {
    return true;
  }

}
