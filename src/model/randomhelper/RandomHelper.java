package model.randomhelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.Treasure;
import model.graph.Coordinate;

/**
 * Random Helper interface serves as the center of generating random
 * results for all purposes in the game.
 *
 */
public interface RandomHelper {
  
  /**
   * Get a randomly generated value with the left (inclusive) and
   * right (inclusive) range.
   *
   * @param left left bound (inclusive)
   * @param right right bound (inclusive)
   * @return generated value
   */
  int randomInt(int left, int right);
  
  
  Coordinate randomCoord(int maxRow, int maxCol);
  
  /**
   * Split one value into several parts and return them.
   *
   * @param total the to-be-split value
   * @param parts number of parts
   * @return List containing the parts of the value
   */
  List<Integer> randDivideVal(Integer total, int parts);
  
  Coordinate coordChoice(Collection<Coordinate> coors);
  
  Coordinate randomCave(boolean[][][] adj);
  
  Map<Treasure, Integer> treasureChoices(int maxNum);
}
