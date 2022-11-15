package model.player;

import model.TreasureHolder;
import model.graph.Coordinate;

/**
 * Player interface represents the player in the dungeon.
 *
 */
public interface Player extends TreasureHolder {
  
  /**
   * Get the player's name.
   *
   * @return name
   */
  String getName();
  
  /**
   * Get the Row player is in.
   *
   * @return row
   */
  int getRow();
  
  /**
   * Get column the player is in.
   *
   * @return column
   */
  int getCol();
  
  /**
   * Get coordinate of the player.
   *
   * @return coordinate
   */
  Coordinate getCoord();
  
  /**
   * Set row.
   *
   * @param newRow new row
   */
  void setRow(int newRow);
  
  /**
   * Set column.
   *
   * @param newCol new column
   */
  void setCol(int newCol);
}
