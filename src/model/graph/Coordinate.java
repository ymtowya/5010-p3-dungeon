package model.graph;

public class Coordinate {
  int row;
  int col;
  
  public Coordinate(int r, int c) {
    this.row = r;
    this.col = c;
  }

  /**
   * @return the row
   */
  public int getRow() {
    return row;
  }

  /**
   * @param row the row to set
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * @return the col
   */
  public int getCol() {
    return col;
  }

  /**
   * @param col the col to set
   */
  public void setCol(int col) {
    this.col = col;
  }
  
}
