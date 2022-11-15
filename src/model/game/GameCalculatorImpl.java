package model.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.Direction;
import model.Treasure;
import model.dungeon.DungeonMap;
import model.dungeon.DungeonMapImpl;
import model.graph.Coordinate;
import model.graph.GridGenerator;
import model.graph.KruskalGridGenerator;
import model.player.Player;
import model.randomhelper.RandomHelper;

/**
 * An implementation of the Game Calculator interface.
 *
 *
 */
public class GameCalculatorImpl implements GameCalculator {
  
  private RandomHelper rh;
  
  /**
   * Initialize the calculator with the random helper.
   *
   * @param r random helper
   */
  public GameCalculatorImpl(RandomHelper r) {
    this.rh = r;
  }

  private Coordinate getMapNextCoor(DungeonMap m, Coordinate c, Direction d) {
    final int rows = m.getRows();
    final int cols = m.getCols();
    int r0 = c.getRow();
    int c0 = c.getCol();
    switch (d) {
      case NORTH:
        r0 = (r0 - 1 + rows) % rows;
        break;
      case EAST:
        c0 = (c0 + 1) % cols;
        break;
      case SOUTH:
        r0 = (r0 + 1) % rows;
        break;
      case WEST:
        c0 = (c0 - 1 + cols) % cols;
        break;
      default:
        break;
    }
    return new Coordinate(r0, c0);
  }
  
  private boolean setPlayerCoord(DungeonMap m, Player p, Coordinate c) {
    p.setRow(c.getRow() % m.getRows());
    p.setCol(c.getCol() % m.getCols());
    return true;
  }
  
  private void setTreasures(GridGenerator g, DungeonMap m, double p) {
    Set<Coordinate> coors = g.geneRandomCaves(p);
    Map<Coordinate, Map<Treasure, Integer>> res = new HashMap<>();
    for (Coordinate c : coors) {
      res.put(c, rh.treasureChoices(3));
    }
    m.setTreasures(res);
  }
  
  @Override
  public DungeonMap initGame(int row, int col, int conn,
      boolean isWrap, double treasureProb) {
    
    GridGenerator genetor = new KruskalGridGenerator(rh);
    genetor.setIsWrapped(isWrap);
    genetor.setSize(row, col);
    genetor.setConnectivity(conn);
    genetor.setRandomStart();
    genetor.geneGrid();
    genetor.setStart(rh.randomCave(genetor.getPlainGrid()));
    genetor.getStepRecords(genetor.getStart());
    genetor.setRandomEnd(5);
    
    DungeonMap theMap = new DungeonMapImpl(row, col, conn, isWrap);
    theMap.setByAdjMap(genetor.getPlainGrid());
    theMap.setStart(genetor.getStart());
    theMap.setEnd(genetor.getEnd());
    setTreasures(genetor, theMap, treasureProb);
    return theMap;
  }

  @Override
  public void enterGame(DungeonMap m, Player p) {
    setPlayerCoord(m, p, m.getStart());
  }
  
  private Coordinate getPlayerCoord(Player p) {
    return new Coordinate(p.getRow(), p.getCol());
  }

  @Override
  public boolean walkPlayer(DungeonMap m, Player p, Direction d) {
    // decide if walkable
    if (m.canWalk(this.getPlayerCoord(p), d)) {
      // walk
      Coordinate nextCoord = this.getMapNextCoor(m, getPlayerCoord(p), d);
      setPlayerCoord(m, p, nextCoord);
      return true;
    }
    return false;
  }

  @Override
  public boolean pickTreasure(DungeonMap m, Player p) {
    if (m.isCave(getPlayerCoord(p))) {
      p.addTreasures(m.getTreasuresAt(getPlayerCoord(p)));
      m.removeTreauseAt(getPlayerCoord(p));
      return true;
    }
    return false;
  }
  
  private int countTreasures(Map<Treasure, Integer> m) {
    int res = 0;
    for (int c : m.values()) {
      res += c;
    }
    return res;
  }

  @Override
  public String getMapString(DungeonMap m, Player p) {
    StringBuilder sb = new StringBuilder();
    Coordinate c = new Coordinate(0, 0);
    for (int i = 0; i < m.getRows(); ++i) {
      final int col = m.getCols();
      c.setRow(i);
      for (int j = 0; j < col; ++j) {
        c.setCol(j);
        if (m.canWalk(c, Direction.NORTH)) {
          sb.append("  |  ");
        } else {
          sb.append("     ");
        }
      }
      sb.append('\n');
      for (int j = 0; j < col; ++j) {
        c.setCol(j);
        if (m.canWalk(c, Direction.WEST)) {
          sb.append("-");
        } else {
          sb.append(" ");
        }
        if (m.getStart().equals(c)) {
          sb.append('[');
        } else if (m.isCave(c)) {
          sb.append('(');
        } else {
          sb.append(' ');
        }
        if (p.getCoord().equals(c)) {
          sb.append('*');
        } else {
          sb.append(countTreasures(m.getTreasuresAt(c)));
        }
        if (m.getEnd().equals(c)) {
          sb.append(']');
        } else if (m.isCave(c)) {
          sb.append(')');
        } else {
          sb.append(' ');
        }
        if (m.canWalk(c, Direction.EAST)) {
          sb.append("-");
        } else {
          sb.append(" ");
        }
      }
      sb.append('\n');
      for (int j = 0; j < col; ++j) {
        c.setCol(j);
        if (m.canWalk(c, Direction.SOUTH)) {
          sb.append("  |  ");
        } else {
          sb.append("     ");
        }
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  @Override
  public String getPlayerString(Player p) {
    StringBuilder sb = new StringBuilder();
    sb.append("---- Player Description ----\n");
    sb.append("Name : ");
    sb.append(p.getName());
    sb.append("\nPosition : ");
    sb.append(p.getCoord().toString());
    sb.append("\nTreasures : \n");
    sb.append(p.getTreasures().toString());
    sb.append("\n---- End Of Description ----\n");
    return sb.toString();
  }

  @Override
  public String getLocationString(DungeonMap m, Coordinate c) {
    StringBuilder sb = new StringBuilder();
    sb.append("---- Location Description ----\n");
    sb.append("Player is currently at : ");
    sb.append(c.toString());
    sb.append("\nThis place is a ");
    if (m.isCave(c)) {
      sb.append("cave");
      sb.append("\nTreasures at this location : \n");
      sb.append(m.getTreasuresAt(c).toString());
    } else {
      sb.append("tunnel\nNo treasures in tunnel.");
    }
    sb.append("\n---- End Of Description ----\n");
    return sb.toString();
  }

  @Override
  public boolean isAtEnd(DungeonMap m, Player p) {
    return m.getEnd().equals(p.getCoord());
  }

  @Override
  public Set<Direction> walkableDirs(DungeonMap m, Player p) {
    return m.getDirectionsAt(p.getCoord());
  }

}
