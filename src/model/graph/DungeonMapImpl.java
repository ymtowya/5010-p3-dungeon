package model.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Direction;
import model.Treasure;

public class DungeonMapImpl implements DungeonMap {
  
  private List<List<Location>> myDungeon;
  private boolean isWrapped;
  private int innerConnectivity;
  private Coordinate start;
  private Coordinate end;
  
  public DungeonMapImpl(int row, int col,
      int connectivity, boolean wrapped) {
    this.myDungeon = new ArrayList<>(row);
    for (int i = 0; i < row; ++i) {
      List<Location> tmpList = new ArrayList<>(col);
      for (int j = 0; j < col; ++j) {
        tmpList.add(new LocationImpl());
      }
      myDungeon.add(tmpList);
    }
    this.innerConnectivity = connectivity;
    this.isWrapped = wrapped;
  }
  
  private void setConnect(int r, int c) {
    // based on inner connectivity & isWrapped
    // have representation
    // 0, 1, 2, 3 - N, E, S, W
    boolean[][][] maps = new boolean[r][c][4];
    for (int i = 0; i < r; ++i) {
      for (int j = 0; j < c; ++j) {
        for (int d = 0; d < 4; ++d) {
          maps[i][j][d] = true; 
        }
      }
    }
    if (!isWrapped) {
      for (int j = 0; j < c; ++j) {
        maps[0][j][0] = false;
        maps[r - 1][j][2] = false;
      }
      for (int i = 0; i < r; ++i) {
        maps[i][0][3] = false;
        maps[i][c - 1][1] = false;
      }
    }
    // get MST
    
    // add to connectivity
    
    // reflect to model
    
  }
  
  private Location locationAt(Coordinate c) {
    return this.myDungeon.get(c.getRow()).get(c.getCol());
  }

  @Override
  public Map<Treasure, Integer> getTreasuresAt(Coordinate c) {
    return locationAt(c).getTreasures();
  }

  @Override
  public boolean canWalk(Coordinate c, Direction d) {
    return locationAt(c).canWalk(d);
  }

  @Override
  public Set<Direction> getDirectionsAt(Coordinate c) {
    return locationAt(c).getAllDirections();
  }

  @Override
  public boolean setRandomTreasures(double p) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int getConnectivity() {
    return this.innerConnectivity;
  }

  @Override
  public boolean isWrapped() {
    return this.isWrapped;
  }

  @Override
  public Coordinate getStart() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Coordinate getEnd() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getCaveCount() {
    int count = 0;
    for (List<Location> row : myDungeon) {
      for (Location l : row) {
        if (l instanceof LocationImpl) {
          ++count;
        }
      }
    }
    return count;
  }

  @Override
  public int getTunnelCount() {
    int count = 0;
    for (List<Location> row : myDungeon) {
      for (Location l : row) {
        if (l instanceof Tunnel) {
          ++count;
        }
      }
    }
    return count;
  }

  @Override
  public int getClassCount(Object o) {
    int count = 0;
    for (List<Location> row : myDungeon) {
      for (Location l : row) {
        if (l.getClass().equals(o)) {
          ++count;
        }
      }
    }
    return count;
  }

}