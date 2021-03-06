package model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.Platform;


public class Character extends Pane {
    Logger logger =  LoggerFactory.getLogger(Character.class);
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    int yVelocity = 0;
    int xVelocity = 0;
    int maxa = 1;
    int maxy = 1;
    int maxVeloX = 7;
    int maxVeloY = 17;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    private Image CharacterImg;
    boolean canJump = false;
    boolean isJumping = false;
    private int x;
    private int y;
    private AnimatedSprite imageView;
    private KeyCode leftKey, rightKey, upKey;

    public Character(int x, int y, int offsetX, int offSetY, KeyCode lk, KeyCode rk, KeyCode uk) {
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.CharacterImg = new Image(getClass().getResourceAsStream("/assets/MarioSheet.png"));
        this.imageView = new AnimatedSprite(CharacterImg, 4, 4, 1, offsetX, offSetY, 16, 32);
        this.leftKey = lk;
        this.rightKey = rk;
        this.upKey = uk;
        this.getChildren().addAll(this.imageView);
    }

    public void moveX() {
        setTranslateX(x);
        if (isMoveLeft) {
            xVelocity = xVelocity >= maxVeloX ? maxVeloX : xVelocity + maxa;
            x = x - xVelocity;
        }
        if (isMoveRight) {
            xVelocity = xVelocity >= maxVeloX ? maxVeloX : xVelocity + maxa;
            x = x + xVelocity;
        }
    }

    public void moveY() {
        setTranslateY(y);
        if (isFalling) {
            yVelocity = yVelocity >= maxVeloY ? maxVeloY : yVelocity + maxy;
            y = y + yVelocity;
        } else if (isJumping) {
            yVelocity = xVelocity <= 0 ? 0 : yVelocity - maxy;
            y = y - yVelocity;
        }
    }

    public void checkReachFloor() {
        if (isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT + 32) {
            isFalling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void repaint() {
        moveY();
        moveX();
    }

    public void jump() {
        if (canJump) {
            yVelocity = maxVeloY;
            canJump = false;
            isJumping = true;
            isFalling = false;
        }
    }

    public void checkReachHighest() {
        if (isJumping && yVelocity <= 0) {
            isJumping = false;
            isFalling = true;
            yVelocity = 0;
        }
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public void moveLeft() {
        xVelocity = 3;
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void moveRight() {
        xVelocity = 3;
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void stop() {
        isMoveRight = false;
        isMoveLeft = false;
    }

    public void checkReachGameWall() {
        if (x <= 0) {
            x = 0;
        } else if (x + getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH - (int) getWidth();
        }
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}", x, y, xVelocity, yVelocity);
    }
}
