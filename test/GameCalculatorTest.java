import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;
import model.Direction;
import model.Treasure;
import model.dungeon.DungeonMap;
import model.game.GameCalculator;
import model.game.GameCalculatorImpl;
import model.graph.Coordinate;
import model.player.DungeonPlayer;
import model.player.Player;
import model.randomhelper.RandomHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the game calculator.
 *
 *
 */
public class GameCalculatorTest {

  private GameCalculator gc;
  private DungeonMap dm;
  private DungeonMap dm2;
  private Player p1;
  
  /**
   * Set up the game and maps.
   *
   * @throws Exception when errored
   */
  @Before
  public void setUp() throws IOException {
    RandomHelper r = new FakeRandomGenerator(7);
    gc = new GameCalculatorImpl(r);
    dm = gc.initGame(6, 7, 3, false, 0.5);
    dm2 = gc.initGame(6, 7, 4, true, 0.5);
    p1 = new DungeonPlayer(0, 0, "Terry");
  }
  
  @Test
  public void testNonWrap() {
    for (int i = 0; i < 7; ++i) {
      dm.setStart(new Coordinate(0, i));
      gc.enterGame(dm, p1);
      assertFalse(gc.walkableDirs(dm, p1).contains(Direction.NORTH));
      dm.setStart(new Coordinate(5, i));
      gc.enterGame(dm, p1);
      assertFalse(gc.walkableDirs(dm, p1).contains(Direction.SOUTH));
    }
    for (int i = 0; i < 6; ++i) {
      dm.setStart(new Coordinate(i, 0));
      gc.enterGame(dm, p1);
      assertFalse(gc.walkableDirs(dm, p1).contains(Direction.WEST));
      dm.setStart(new Coordinate(i, 6));
      gc.enterGame(dm, p1);
      assertFalse(gc.walkableDirs(dm, p1).contains(Direction.EAST));
    }
  }
  
  @Test
  public void testWrap() {
    boolean atLeastOnce = false;
    for (int i = 0; i < 7; ++i) {
      dm2.setStart(new Coordinate(0, i));
      gc.enterGame(dm2, p1);
      atLeastOnce = atLeastOnce || gc.walkableDirs(dm2, p1).contains(Direction.NORTH);
      dm2.setStart(new Coordinate(5, i));
      gc.enterGame(dm2, p1);
      atLeastOnce = atLeastOnce || gc.walkableDirs(dm2, p1).contains(Direction.SOUTH);
    }
    assertTrue(atLeastOnce);
    atLeastOnce = false;
    for (int i = 0; i < 6; ++i) {
      dm2.setStart(new Coordinate(i, 0));
      gc.enterGame(dm2, p1);
      atLeastOnce = atLeastOnce || gc.walkableDirs(dm2, p1).contains(Direction.WEST);
      dm2.setStart(new Coordinate(i, 6));
      gc.enterGame(dm2, p1);
      atLeastOnce = atLeastOnce || gc.walkableDirs(dm2, p1).contains(Direction.EAST);
    }
    assertTrue(atLeastOnce);
  }
  
  @Test
  public void testPercent() {
    int caves = 0;
    int treas = 0;
    for (int i = 0; i < 6; ++i) {
      for (int j = 0; j < 7; ++j) {
        if (dm.isCave(new Coordinate(i, j))) {
          ++caves;
        }
        if (!dm.getTreasuresAt(new Coordinate(i, j)).isEmpty()) {
          ++treas;
        }
      }
    }
    assertTrue(caves * 0.5 <= treas);
  }
  
  @Test
  public void testStart() {
    gc.enterGame(dm, p1);
    assertEquals(dm.getStart(), p1.getCoord());
  }
  
  @Test
  public void testPlayerDescribe() {
    gc.enterGame(dm, p1);
    assertEquals("---- Player Description ----\n"
        + "Name : Terry\n"
        + "Position : (5, 6)\n"
        + "Treasures : \n"
        + "{SAPPHIRES=0, DIAMONDS=0, RUBIES=0}\n"
        + "---- End Of Description ----\n", gc.getPlayerString(p1));
  }
  
  @Test
  public void testLocationDescribe() {
    dm.setStart(new Coordinate(5, 6));
    gc.enterGame(dm, p1);
    assertEquals("---- Location Description ----\n"
        + "Player is currently at : (5, 6)\n"
        + "This place is a cave\n"
        + "Treasures at this location : \n"
        + dm.getTreasuresAt(dm.getStart()).toString()
        + "\n---- End Of Description ----\n", gc.getLocationString(dm, dm.getStart()));
    // System.out.println(gc.getMapString(dm, p));
    // System.out.println(gc.getLocationString(dm, dm.getStart()));
  }
  
  @Test
  public void testWalable() {
    dm.setStart(new Coordinate(3, 0));
    gc.enterGame(dm, p1);
    if (gc.walkPlayer(dm, p1, Direction.EAST)) {
      assertEquals(new Coordinate(3, 1), p1.getCoord());
    }
  }
  
  @Test
  public void testPickUp() {
    dm.setStart(new Coordinate(3, 0));
    gc.enterGame(dm, p1);
    assertEquals(0, p1.getTotalTreasure());
    Map<Treasure, Integer> rs = dm.getTreasuresAt(dm.getStart());
    int acc = 0;
    for (int v : rs.values()) {
      acc += v;
    }
    gc.pickTreasure(dm, p1);
    assertEquals(acc, p1.getTotalTreasure());
  }

}
