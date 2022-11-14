package model.game;

import model.Direction;
import model.dungeon.DungeonMap;
import model.player.Player;
import model.randomhelper.RandomHelper;

public interface GameCalculator {
  
  DungeonMap initGame(int row, int col, int conn,
      boolean isWrap, double treasureProb);
  void enterGame(DungeonMap m, Player p);
  boolean walkPlayer(DungeonMap m, Player p, Direction d);
  boolean pickTreasure(DungeonMap m, Player p);
  String getMapString(DungeonMap m, Player p);

}
