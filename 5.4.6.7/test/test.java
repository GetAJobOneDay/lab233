package test;

import controller.DrawingLoop;
import controller.GameLoop;
import de.saxsys.mvvmfx.testingutils.jfxrunner.JfxRunner;
import javafx.scene.input.KeyCode;
import model.Character;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import view.Platform;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class test {
    private Character floatingCharacter;
    private Character rockman;
    private ArrayList<Character> characterListUnderTest;
    private Platform platformUnderTest;
    private GameLoop gameLoopUnderTest;
    private DrawingLoop drawingLoopUnderTest;
    private Method updateMethod;
    private Method redrawMethod;
    private Method colided;
    @Before
    public void setup() {
        floatingCharacter = new Character(390,225, 0, 0, KeyCode.A, KeyCode.D, KeyCode.W, "MarioSheet", 64, 32);
        rockman = new Character(390,200, 0, 0, KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, "rockmanSheet", 65, 75);
        characterListUnderTest = new ArrayList<Character>();
        characterListUnderTest.add(floatingCharacter);
        characterListUnderTest.add(rockman);
        platformUnderTest = new Platform();
        gameLoopUnderTest = new GameLoop(platformUnderTest);
        drawingLoopUnderTest = new DrawingLoop(platformUnderTest);
        try {
            colided = DrawingLoop.class.getDeclaredMethod("checkCollision",ArrayList.class);
            updateMethod = GameLoop.class.getDeclaredMethod("update", Character.class);
            redrawMethod = DrawingLoop.class.getDeclaredMethod("paint", ArrayList.class);
            colided.setAccessible(true);
            updateMethod.setAccessible(true);
            redrawMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            updateMethod = null;
            redrawMethod = null;
            colided =null;
        }
    }
    @Test
    public void characterStompOthersMustCollapse() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException, InterruptedException {
        Character characterWAD = characterListUnderTest.get(0);
        Character characterURL = characterListUnderTest.get(1);
        int expected =30;
        /*platformUnderTest.getKeys().add(KeyCode.W);
        platformUnderTest.getKeys().add(KeyCode.D);
        Field isJump = characterWAD.getClass().getDeclaredField("isFalling");*/
        for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        Field isMoveRight = characterWAD.getClass().getDeclaredField("isMoveRight");
        //isJump.setAccessible(true);
        //isMoveRight.setAccessible(true);
        /*assertTrue("Controller: right key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.D));
        assertTrue("Controller: jump key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.W));
        assertTrue("Model: Character move up state is set", isJump.getBoolean(characterWAD));
        assertTrue("Model: Character move right state is set", isMoveRight.getBoolean(characterWAD));*/
        colided.invoke(drawingLoopUnderTest,characterListUnderTest);
        Thread.sleep(800);
        assertEquals("View: others Character got stomp with main character",expected,characterWAD.getX());
        //assertEquals("View: Character stuck with another character",expected,characterWAD.getY());
    }
    @Test
    public void characterShouldMoveToTheLeftAfterTheLeftKeyIsPressed() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Character characterUnderTest = characterListUnderTest.get(0);
        int startX = characterUnderTest.getX();
        platformUnderTest.getKeys().add(KeyCode.A);
        for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest, characterListUnderTest);
        Field isMoveLeft = characterUnderTest.getClass().getDeclaredField("isMoveLeft");
        isMoveLeft.setAccessible(true);
        assertTrue("Controller: Left key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.A));
        assertTrue("Model: Character moving left state is set", isMoveLeft.getBoolean(characterUnderTest));
        assertTrue("View: Character is moving left", characterUnderTest.getX() < startX);
    }
    @Test
    public void characterInitialValuesShouldMatchConstructorArguments(){
        assertEquals("Initial x", 30, floatingCharacter.getX(), 0);
        assertEquals("Initial y", 30, floatingCharacter.getY(), 0);
        assertEquals("Offset x", 0, floatingCharacter.getOffsetX(), 0.0);
        assertEquals("Offset y", 0, floatingCharacter.getOffsetY(), 0.0);
        assertEquals("Left key", KeyCode.A, floatingCharacter.getLeftKey());
        assertEquals("Right key", KeyCode.D, floatingCharacter.getRightKey());
        assertEquals("Up key", KeyCode.W, floatingCharacter.getUpKey());
    }
    @Test
    public void characterMustMoveRightAfterKeyPressed() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Character characterWAD = characterListUnderTest.get(0);
        int startX = characterWAD.getX();
        platformUnderTest.getKeys().add(KeyCode.D);

            for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        Field isMoveRight = characterWAD.getClass().getDeclaredField("isMoveRight");
        isMoveRight.setAccessible(true);

            assertTrue("Controller: right key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.D));
            assertTrue("Model: Character moving right state is set", isMoveRight.getBoolean(characterWAD));
            assertTrue("View: Character is moving right", characterWAD.getX() > startX);
        }
    @Test
    public void characterMustJumpAfterKeyPressed() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Character characterWAD = characterListUnderTest.get(0);
        int ground = Platform.GROUND- Character.CHARACTER_HEIGHT;
        platformUnderTest.getKeys().add(KeyCode.W);
        for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        Field isMoveJump = characterWAD.getClass().getDeclaredField("canJump");
        isMoveJump.setAccessible(true);
            assertTrue("Controller: jump key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.W));
            assertFalse("Model: Character jump state is set", isMoveJump.getBoolean(characterWAD));
            assertTrue("View: Character is jumping", characterWAD.getY() <= ground);
    }
    @Test
    public void characterCantJumpWhileInTheAir() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Character characterWAD = characterListUnderTest.get(0);
        int startY = characterWAD.getY();
        platformUnderTest.getKeys().add(KeyCode.W);
        for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        Field isMoveJump = characterWAD.getClass().getDeclaredField("isFalling");
        isMoveJump.setAccessible(true);
        //if(characterWAD.isFalling()){
            assertTrue("Controller: jump key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.W));
            assertTrue("Model: Character jump state is set", isMoveJump.getBoolean(characterWAD));
            assertTrue("View: Character is jumping but y not change", characterWAD.getY() > startY);

        //}
    }
    @Test
    public void characterMustStayAtLeastBorder() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException, InterruptedException {
        Character characterWAD = characterListUnderTest.get(0);
        Character characterURL = characterListUnderTest.get(1);
        int maxXRight = Platform.WIDTH-Character.CHARACTER_WIDTH;
        int maxXLeft = 0;
        platformUnderTest.getKeys().add(KeyCode.D);
        for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);

        Field isReachBorder = characterWAD.getClass().getDeclaredField("isMoveRight");
        isReachBorder.setAccessible(true);
        assertTrue("Controller: right key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.D));
        assertTrue("Model: Character move right state is set", isReachBorder.getBoolean(characterWAD));
        assertEquals("View: Character stuck with the right wall", maxXRight,characterWAD.getX());
        platformUnderTest.getKeys().add(KeyCode.A);
        assertTrue("Controller: right key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.A));
        assertTrue("Model: Character move right state is set", isReachBorder.getBoolean(characterURL));
        assertEquals("View: Character stuck with right wall",maxXLeft,characterURL.getX());
    }
    @Test
    public void characterTouchEachMustStop() throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Character characterWAD = characterListUnderTest.get(0);
        Character characterURL = characterListUnderTest.get(1);
        int begin = characterWAD.getX();
        platformUnderTest.getKeys().add(KeyCode.D);
        for (Character character : characterListUnderTest) {
            updateMethod.invoke(gameLoopUnderTest, character);
        }
        redrawMethod.invoke(drawingLoopUnderTest,characterListUnderTest);
        Field isReachBorder = characterWAD.getClass().getDeclaredField("isMoveRight");
        isReachBorder.setAccessible(true);
        assertTrue("Controller: right key pressing is acknowledged", platformUnderTest.getKeys().isPressed(KeyCode.D));
        assertTrue("Model: Character move right state is set", isReachBorder.getBoolean(characterWAD));
        colided.invoke(drawingLoopUnderTest,characterListUnderTest);
        assertEquals("View: Character stuck with another character", begin,characterWAD.getX());
    }

}




