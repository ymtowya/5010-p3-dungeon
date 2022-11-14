package model.dungeon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Direction;
import model.Treasure;
import model.graph.Coordinate;

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
  
  private void setMap(boolean[][][] adjs) {
    int r = adjs.length;
    if (r < 1) {
      return;
    }
    int c = adjs[0].length;
    // myDungeon.clear();
    for (int i = 0; i < r; ++i) {
      List<Location> locaList = this.myDungeon.get(i);
      for (int j = 0; j < c; ++j) {
        Location location = locaList.get(j);
        location.clearAllDirections();
        if (adjs[i][j][0]) {
          location.addNewDirection(Direction.NORTH);
        }
        if (adjs[i][j][1]) {
          location.addNewDirection(Direction.EAST);
        }
        if (adjs[i][j][2]) {
          location.addNewDirection(Direction.SOUTH);
        }
        if (adjs[i][j][3]) {
          location.addNewDirection(Direction.WEST);
        }
      }
    }
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
  public int getConnectivity() {
    return this.innerConnectivity;
  }

  @Override
  public boolean isWrapped() {
    return this.isWrapped;
  }

  @Override
  public Coordinate getStart() {
    return new Coordinate(start);
  }

  @Override
  public Coordinate getEnd() {
    return new Coordinate(end);
  }

  @Override
  public int getCaveCount() {
    int count = 0;
    for (List<Location> row : myDungeon) {
      for (Location l : row) {
        if (l.isCave()) {
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
        if (!l.isCave()) {
          ++count;
        }
      }
    }
    return count;
  }

  @Override
  public void setByAdjMap(boolean[][][] adjs) {
    this.setMap(adjs);
  }

  @Override
  public boolean setTreasures(Map<Coordinate, Map<Treasure, Integer>> trs) {
    if (trs == null) {
      return false;
    }
    for (Coordinate c : trs.keySet()) {
      if (locationAt(c).canHoldTreasureNow()) {
        locationAt(c).setTreasureMap(trs.get(c));
      } else {
        return false;
      }
    }
    return true;
  }

  @Override
  public void setStart(Coordinate s) {
    this.start = new Coordinate(s);
  }

  @Override
  public void setEnd(Coordinate e) {
    this.end = new Coordinate(e);
  }

  @Override
  public int getRows() {
    if (this.myDungeon == null) {
      return 0;
    }
    return this.myDungeon.size();
  }

  @Override
  public int getCols() {
    if (getRows() > 0) {
      return this.myDungeon.get(0).size();
    }
    return 0;
  }

  @Override
  public boolean isCave(Coordinate c) {
    if (c == null || this.myDungeon == null) {
      return false;
    }
    Location l = myDungeon.get(c.getRow()).get(c.getCol());
    return l.isCave();
  }

  @Override
  public void removeTreauseAt(Coordinate c) {
    locationAt(c).setTreasureMap(new HashMap<>());
  }

}
