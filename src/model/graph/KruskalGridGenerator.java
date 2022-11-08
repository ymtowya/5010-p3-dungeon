package model.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.function.IntPredicate;

import model.Direction;

public class KruskalGridGenerator implements GridGenerator {

  private int row;
  private int col;
  private int connectivity;
  private boolean isWrapped;
  private boolean[][][] grid;
  private boolean[][][] adj;
  private Coordinate start;
  private Coordinate end;
  private Random random;
  
  public KruskalGridGenerator() {
    this.row = 5;
    this.col = 7;
    this.connectivity = 0;
    this.isWrapped = true;
    this.start = new Coordinate(2, 3);
    this.end = new Coordinate(0, 1);
    this.random = new Random();
  }
  
  @Override
  public void setSize(int r, int c) {
    this.row = r;
    this.col = c;
  }

  @Override
  public void setConnectivity(int c) {
    this.connectivity = c;
  }

  @Override
  public void setIsWrapped(boolean i) {
    this.isWrapped = i;
  }
  
  private int getOppDir(int d) {
    return (d + 2) % 4;
  }
  
  private int modRow(Coordinate c) {
    return (c.getRow() + row) % row;
  }
  
  private int modCol(Coordinate c) {
    return (c.getCol() + col) % col;
  }
  
  private boolean canWalk(Coordinate c, int d) {
    return grid[modRow(c)][modCol(c)][d % 4];
  }
  
  private Coordinate getNextCoor(Coordinate c, int d) {
    int r0 = c.getRow();
    int c0 = c.getCol();
    switch (d % 4) {
      case 0:
        r0 = (r0 - 1 + row) % row;
        break;
      case 1:
        c0 = (c0 + 1) % col;
        break;
      case 2:
        r0 = (r0 + 1) % row;
        break;
      case 3:
        c0 = (c0 - 1 + col) % col;
        break;
      default:
        break;
    }
    return new Coordinate(r0, c0);
  }
  
  private boolean setAdjWalk(Coordinate c, int d) {
    if (!canWalk(c, d)) {
      return false;
    }
    adj[modRow(c)][modCol(c)][d % 4] = true;
    Coordinate newCoor = getNextCoor(c, d);
    adj[modRow(newCoor)][modCol(newCoor)][getOppDir(d)] = true;
    return true;
  }
  
  private void initGrid() {
    this.grid = new boolean[row][col][4];
    for (int i = 0; i < row; ++i) {
      for (int j = 0; j < col; ++j) {
        for (int d = 0; d < 4; ++d) {
          grid[i][j][d] = true; 
        }
      }
    }
    if (!isWrapped) {
      for (int j = 0; j < col; ++j) {
        grid[0][j][0] = false;
        grid[row - 1][j][2] = false;
      }
      for (int i = 0; i < row; ++i) {
        grid[i][0][3] = false;
        grid[i][col - 1][1] = false;
      }
    }
  }
  
  private Coordinate getRandomCoor(Set<Coordinate> coors) {
    int index = random.nextInt(coors.size());
    Iterator<Coordinate> iter = coors.iterator();
    for (int i = 0; i < index; i++) {
        iter.next();
    }
    return iter.next();
  }
  
  private void geneMST() {
    // init vSet & adjVSet
    Set<Coordinate> connected = new HashSet<>();
    Set<Coordinate> adjv = new HashSet<>();
    connected.add(this.start);
    // init adj
    this.adj = new boolean[row][col][4];
    for (int i = 0; i < row; ++i) {
      for (int j = 0; j < col; ++j) {
        for (int d = 0; d < 4; ++d) {
          adj[i][j][d] = false; 
        }
      }
    }
    for (int d = 0; d < 4; ++d) {
      // setAdjWalk(this.start, d);
      if (canWalk(this.start, d)) {
        adjv.add(getNextCoor(this.start, d));
      }
    }
    // loop
    while (connected.size() < row * col) {
      Coordinate curr = getRandomCoor(adjv);
      int d = random.nextInt(4);
      for (int i = d; i < d + 4; ++i) {
        if (connected.contains(getNextCoor(curr, i))
            && canWalk(curr, i)) {
          if (setAdjWalk(curr, i)) {
            connected.add(curr);
            adjv.remove(curr);
            break;
          }
        }
      }
      for (int i = 0; i < 4; ++i) {
        Coordinate tmp = getNextCoor(curr, i);
        if (!(connected.contains(tmp))
            && !(adjv.contains(tmp))
            && canWalk(curr, i)) {
          adjv.add(tmp);
        }
      }
    }
  }
  
  @Override
  public void geneGrid() {
    // based on inner connectivity & isWrapped
    // have representation
    // 0, 1, 2, 3 - N, E, S, W
    this.initGrid();
    // get MST
    this.geneMST();
    // add to connectivity
    
  }

  @Override
  public boolean[][][] getPlainGrid() {
    boolean[][][] gridRes = new boolean[row][col][4];
    for (int i = 0; i < row; ++i) {
      for (int j = 0; j < col; ++j) {
        for (int d = 0; d < 4; ++d) {
          gridRes[i][j][d] = adj[i][j][d]; 
        }
      }
    }
    return gridRes;
  }

  @Override
  public void setStart(Coordinate newStart) {
    this.start = new Coordinate(newStart);
  }

  @Override
  public void setEnd(Coordinate newEnd) {
    this.end = new Coordinate(newEnd);
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
  public String getAdjStr() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < row; ++i) {
      for (int j = 0; j < col; ++j) {
        if (adj[i][j][0]) {
          sb.append(" | ");
        } else {
          sb.append("   ");
        }
      }
      sb.append('\n');
      for (int j = 0; j < col; ++j) {
        if (adj[i][j][3]) {
          sb.append("-O");
        } else {
          sb.append(" O");
        }
        if (adj[i][j][1]) {
          sb.append("-");
        } else {
          sb.append(" ");
        }
      }
      sb.append('\n');
      for (int j = 0; j < col; ++j) {
        if (adj[i][j][2]) {
          sb.append(" | ");
        } else {
          sb.append("   ");
        }
      }
      sb.append('\n');
    }
    return sb.toString();
  }

}
