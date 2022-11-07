package model.player;

import model.TreasureHolder;

public interface Player extends TreasureHolder {
  int getId();
  int getRow();
  int getCol();
  void setRow(int newRow);
  void setCol(int newCol);
}
