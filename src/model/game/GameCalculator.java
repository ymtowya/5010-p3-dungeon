package model.game;

import java.util.Set;

import model.Direction;
import model.dungeon.DungeonMap;
import model.graph.Coordinate;
import model.player.Player;
import model.randomhelper.RandomHelper;

public interface GameCalculator {
  
  DungeonMap initGame(int row, int col, int conn,
      boolean isWrap, double treasureProb);
  void enterGame(DungeonMap m, Player p);
  boolean walkPlayer(DungeonMap m, Player p, Direction d);
  boolean pickTreasure(DungeonMap m, Player p);
  String getMapString(DungeonMap m, Player p);
  String getPlayerString(Player p);
  String getLocationString(DungeonMap m, Coordinate c);
  boolean isAtEnd(DungeonMap m, Player p);
  Set<Direction> walkableDirs(DungeonMap m, Player p);
}
