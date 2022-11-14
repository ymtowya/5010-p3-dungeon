import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.graph.GridGenerator;
import model.graph.KruskalGridGenerator;
import model.randomhelper.DungeonRandomHelper;
import model.randomhelper.RandomHelper;

public class GridGeneratorTest {
  
  private GridGenerator genetor;

  @Before
  public void setUp() throws Exception {
    RandomHelper rh = new DungeonRandomHelper(99);
    genetor = new KruskalGridGenerator(rh);
  }

  @Test
  public void test() {
    genetor.setIsWrapped(false);
    genetor.setSize(7, 9);
    genetor.setConnectivity(4);
    genetor.geneGrid();
    genetor.getPlainGrid();
    genetor.setRandomStart();
    System.out.println(genetor.getAdjStr());
    genetor.getStepRecords(genetor.getStart());
    System.out.println(genetor.getStepRecordString(genetor.getStart()));
  }

}
