import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.dungeon.DungeonMap;
import model.game.GameCalculator;
import model.game.GameCalculatorImpl;
import model.player.DungeonPlayer;
import model.player.Player;
import model.randomhelper.DungeonRandomHelper;
import model.randomhelper.RandomHelper;

public class GameCalculatorImplTest {

  private GameCalculator gc;
  private DungeonMap dm;
  private DungeonMap dm2;
  private Player p1;
  
  @Before
  public void setUp() throws Exception {
    RandomHelper r = new DungeonRandomHelper(9);
    gc = new GameCalculatorImpl(r);
    dm = gc.initGame(6, 7, 3, false, 0.5);
    dm2 = gc.initGame(6, 7, 4, true, 0.5);
    p1 = new DungeonPlayer(0, 0, "Tommy");
  }

  @Test
  public void testMove() {
    gc.enterGame(dm, p1);
    System.out.println(gc.getMapString(dm, p1));
    gc.walkPlayer(dm, p1, Direction.SOUTH);
    System.out.println(gc.getMapString(dm, p1));
    gc.pickTreasure(dm, p1);
  }

}
