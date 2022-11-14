import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.Treasure;
import model.dungeon.DungeonMap;
import model.game.GameCalculator;
import model.game.GameCalculatorImpl;
import model.player.DungeonPlayer;
import model.player.Player;
import model.randomhelper.DungeonRandomHelper;
import model.randomhelper.RandomHelper;

public class GameCalculatorTest {

  private GameCalculator gc;
  private DungeonMap dm;
  private Player p;
  
  @Before
  public void setUp() throws Exception {
    RandomHelper r = new DungeonRandomHelper(139);
    gc = new GameCalculatorImpl(r);
    dm = gc.initGame(5, 7, 3, false, 0.5);
    p = new DungeonPlayer(0, 0, "Terry");
  }

  @Test
  public void test() {
    gc.enterGame(dm, p);
    System.out.println(gc.getMapString(dm, p));
    gc.walkPlayer(dm, p, Direction.NORTH);
    System.out.println(gc.getMapString(dm, p));
    gc.walkPlayer(dm, p, Direction.SOUTH);
    gc.pickTreasure(dm, p);
    assertFalse(gc.walkPlayer(dm, p, Direction.SOUTH));
    assertFalse(gc.walkPlayer(dm, p, Direction.SOUTH));
    assertFalse(gc.walkPlayer(dm, p, Direction.SOUTH));
    System.out.println(gc.getMapString(dm, p));
    System.out.println(p.getTreasures());
    assertEquals(0, 0);
    
  }

}
