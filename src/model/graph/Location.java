package model.graph;

import java.util.Set;

import model.Direction;

public interface Location {
  boolean canWalk(Direction d);
  void setDirectsSet(Set<Direction> newDirections);
  void addNewDirection(Direction d);
  void removeDirection(Direction d);
  void clearAllDirections();
}
