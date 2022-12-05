import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.graph.GridGenerator;
import model.graph.KruskalGridGenerator;
import model.randomhelper.DungeonRandomHelper;
import model.randomhelper.RandomHelper;

public class KruskalGridGeneratorTest {
  
  private GridGenerator genetor;

  @Before
  public void setUp() throws Exception {
    RandomHelper randomHelper = new DungeonRandomHelper(14);
    this.genetor = new KruskalGridGenerator(randomHelper);
    genetor.setIsWrapped(false);
    genetor.setSize(6, 6);
    genetor.setConnectivity(4);
    genetor.geneGrid();
    genetor.getPlainGrid();
    genetor.setRandomStart();
  }

  @Test
  public void test1() {
    System.out.println(genetor.getAdjStr());
  }
  
  @Test
  public void test2() {
    System.out.println(genetor.getStepRecordString(genetor.getStart()));
  }

}
