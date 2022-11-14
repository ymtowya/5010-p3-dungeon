package model.player;

import model.TreasureHolder;
import model.graph.Coordinate;

public interface Player extends TreasureHolder {
  int getId();
  int getRow();
  int getCol();
  Coordinate getCoord();
  void setRow(int newRow);
  void setCol(int newCol);
}
