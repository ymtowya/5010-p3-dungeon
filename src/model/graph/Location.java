package model.graph;

import java.util.Set;

import model.Direction;
import model.TreasureHolder;

public interface Location extends TreasureHolder {
  boolean canWalk(Direction d);
  void setDirectsSet(Set<Direction> newDirections);
  void addNewDirection(Direction d);
  void removeDirection(Direction d);
  void clearAllDirections();
  Set<Direction> getAllDirections();
  boolean isTunnel();
}
