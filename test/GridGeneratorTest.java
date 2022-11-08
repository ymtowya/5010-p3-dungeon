import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.graph.GridGenerator;
import model.graph.KruskalGridGenerator;

public class GridGeneratorTest {
  
  private GridGenerator genetor;

  @Before
  public void setUp() throws Exception {
    genetor = new KruskalGridGenerator();
  }

  @Test
  public void test() {
    genetor.setIsWrapped(true);
    genetor.geneGrid();
    genetor.getPlainGrid();
    System.out.println(genetor.getAdjStr());
  }

}
