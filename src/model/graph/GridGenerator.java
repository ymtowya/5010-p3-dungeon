package model.graph;

import java.util.Set;

public interface GridGenerator {
  void setSize(int r, int c);
  void setConnectivity(int c);
  void setIsWrapped(boolean i);
  void setStart(Coordinate newStart);
  void setEnd(Coordinate newEnd);
  boolean setRandomStart();
  Coordinate getStart();
  Coordinate getEnd();
  boolean[][][] getPlainGrid();
  String getAdjStr();
  void geneGrid();
  int[][] getStepRecords(Coordinate s);
  String getStepRecordString(Coordinate s);
  boolean setRandomEnd(int steps);
  Set<Coordinate> geneRandomCaves(double prob);
}
