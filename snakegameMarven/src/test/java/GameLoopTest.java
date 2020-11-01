
import controller.GameLoop;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import model.Food;
import model.Snake;
import org.junit.Before;
import org.junit.Test;
import view.Platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {
    private GameLoop gameLoop;
    private Method update;
    private Method collision;
    private Method redraw;
    @Before
    public void init() throws NoSuchMethodException {
        gameLoop = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),new Food());
        update = GameLoop.class.getDeclaredMethod("update");
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        redraw = GameLoop.class.getDeclaredMethod("redraw");
        update.setAccessible(true);
        collision.setAccessible(true);
        redraw.setAccessible(true);
    }
    private void clockTickHelper() throws InvocationTargetException, IllegalAccessException {
        update.invoke(gameLoop);
        collision.invoke(gameLoop);
        redraw.invoke(gameLoop);
    }
    @Test
    public void testClockTick() throws InvocationTargetException, IllegalAccessException {
        gameLoop = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),new Food());
        clockTickHelper();
        assertEquals(gameLoop.getSnake().getHead(),new Snake(new Point2D(0,1)).getHead());
    }
    @Test
    public void testNoBack() throws InvocationTargetException, IllegalAccessException {
        gameLoop = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),new Food());
        gameLoop.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoop.getSnake().getHead(),new Point2D(0,1));
        gameLoop.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoop.getSnake().getHead(),new Point2D(0,2));
        gameLoop.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoop.getSnake().getHead(),new Point2D(0,3));
    }

}
