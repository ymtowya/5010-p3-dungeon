package model.player;

public interface Player {
  int getId();
  int getRow();
  int getCol();
  void setRow(int newRow);
  void setCol(int newCol);
}
