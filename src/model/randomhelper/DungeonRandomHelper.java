package model.randomhelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Treasure;
import model.graph.Coordinate;


/**
 * BattleRandomHelper is one helper to give random values to support the game.
 *
 *
 */
public class DungeonRandomHelper implements RandomHelper {
  
  Random rand;
  
  public DungeonRandomHelper(int seed) {
    rand = new Random(seed);
  }

  @Override
  public int randomInt(int left, int right) {
    return rand.nextInt(right - left + 1) + left;
  }

  
  private int randTerm() {
    return this.randomInt(5, 50);
  }

  @Override
  public List<Integer> randDivideVal(Integer total, int parts) {
    final int unit = Math.floorDiv(total, parts);
    final int left = total - unit * (parts - 1);
    List<Integer> resultList = new ArrayList<>(parts);
    for (int i = 0; i < parts; ++i) {
      resultList.add(unit);
    }
    resultList.set(randomInt(0, parts - 1), left);
    return resultList;
  }

  @Override
  public Coordinate randomCoord(int maxRow, int maxCol) {
    int r = randomInt(0, maxRow - 1);
    int c = randomInt(0, maxCol - 1);
    return new Coordinate(r, c);
  }

  @Override
  public List<Treasure> randTreasures(int maxNum) {
    // TODO Auto-generated method stub
    return null;
  }

}
