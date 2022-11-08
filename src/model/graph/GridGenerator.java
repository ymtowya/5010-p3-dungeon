package model.graph;

public interface GridGenerator {
  void setSize(int r, int c);
  void setConnectivity(int c);
  void setIsWrapped(boolean i);
  void setStart(Coordinate newStart);
  void setEnd(Coordinate newEnd);
  Coordinate getStart();
  Coordinate getEnd();
  boolean[][][] getPlainGrid();
  String getAdjStr();
  void geneGrid();
}
