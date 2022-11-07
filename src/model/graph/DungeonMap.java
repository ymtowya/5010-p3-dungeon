package model.graph;

import java.util.Map;
import java.util.Set;

import model.Direction;
import model.Treasure;

public interface DungeonMap {
  Map<Treasure, Integer> getTreasuresAt(Coordinate c);
  boolean canWalk(Coordinate c, Direction d);
  Set<Direction> getDirectionsAt(Coordinate c);
  boolean setRandomTreasures(double p);
  int getConnectivity();
  boolean isWrapped();
  Coordinate getStart();
  Coordinate getEnd();
  int getCaveCount();
  int getTunnelCount();
  int getClassCount(Object o);
}
