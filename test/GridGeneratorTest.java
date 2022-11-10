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
    RandomHelper rh = new DungeonRandomHelper(5);
    genetor = new KruskalGridGenerator(rh);
  }

  @Test
  public void test() {
    genetor.setIsWrapped(false);
    genetor.geneGrid();
    genetor.getPlainGrid();
    System.out.println(genetor.getAdjStr());
    genetor.getStepRecords();
    System.out.println(genetor.getStepRecordString());
  }

}
