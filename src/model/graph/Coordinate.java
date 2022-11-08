package model.graph;

public class Coordinate {
  int row;
  int col;
  
  public Coordinate(int r, int c) {
    this.row = r;
    this.col = c;
  }
  
  public Coordinate(Coordinate oldCoor) {
    this.row = oldCoor.getRow();
    this.col = oldCoor.getCol();
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
  
  @Override
  public int hashCode() {
    return this.row * 251 + this.col;
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Coordinate)) {
      return false;
    }
    Coordinate toCompare = (Coordinate) o;
    if (row == toCompare.getRow()
        && col == toCompare.getCol()) {
      return true;
    }
    return false;
  }
  
}
