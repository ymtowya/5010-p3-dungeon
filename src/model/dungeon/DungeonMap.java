package model.dungeon;

import java.util.Map;
import java.util.Set;

import model.Direction;
import model.Treasure;
import model.graph.Coordinate;

public interface DungeonMap {
  Map<Treasure, Integer> getTreasuresAt(Coordinate c);
  boolean canWalk(Coordinate c, Direction d);
  Set<Direction> getDirectionsAt(Coordinate c);
  boolean setTreasures(Map<Coordinate, Map<Treasure, Integer>> trs);
  int getConnectivity();
  boolean isWrapped();
  Coordinate getStart();
  Coordinate getEnd();
  void setStart(Coordinate s);
  void setEnd(Coordinate e);
  int getCaveCount();
  int getTunnelCount();
  void setByAdjMap(boolean[][][] adjs);
  int getRows();
  int getCols();
  boolean isCave(Coordinate c);
  void removeTreauseAt(Coordinate c);
}
