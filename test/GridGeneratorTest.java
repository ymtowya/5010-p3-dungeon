import static org.junit.Assert.assertTrue;

import java.io.IOException;
import model.graph.Coordinate;
import model.graph.GridGenerator;
import model.graph.KruskalGridGenerator;
import model.randomhelper.DungeonRandomHelper;
import model.randomhelper.RandomHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests the grid generator.
 *
 */
public class GridGeneratorTest {
  
  private GridGenerator genetor;

  /**
   * Set up the map and grid generator.
   *
   * @throws IOException when errored
   */
  @Before
  public void setUp() throws IOException {
    RandomHelper rh = new DungeonRandomHelper(99);
    genetor = new KruskalGridGenerator(rh);
    genetor.setIsWrapped(false);
    genetor.setSize(7, 9);
    genetor.setConnectivity(4);
    genetor.geneGrid();
    genetor.getPlainGrid();
    genetor.setRandomStart();
  }
  
  @Test
  public void testReachable() {
    int[][] res = genetor.getStepRecords(genetor.getStart());
    for (int i = 0; i < 7; ++i) {
      for (int j = 0; j < 9; ++j) {
        assertTrue(res[i][j] >= 0);
      }
    }
  }
  
  @Test
  public void testEndDist() {
    int[][] res = genetor.getStepRecords(genetor.getStart());
    Coordinate end = genetor.getEnd();
    assertTrue(res[end.getRow()][end.getCol()] >= 5);
  }
  
  @Test
  public void testEndReachable() {
    int[][] res = genetor.getStepRecords(genetor.getStart());
    Coordinate end = genetor.getEnd();
    assertTrue(res[end.getRow()][end.getCol()] != -1);
  }

  @Test
  public void test() {
    genetor.getStepRecords(genetor.getStart());
    // System.out.println(genetor.getStepRecordString(genetor.getStart()));
  }

}
