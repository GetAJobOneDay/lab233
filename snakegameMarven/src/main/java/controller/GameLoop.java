package controller;


import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Direction;
import model.Food;
import model.Snake;
import view.Platform;


public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f/10;
    private boolean running;
    public GameLoop(Platform platform,Snake snake,Food food){
        this.platform=platform;
        this.snake =snake;
        this.food=food;
        running=true;
    }
    private void update(){
        KeyCode keyCode = Platform.getKeyCode();
        Direction direction = snake.getDirection();
        if(keyCode==KeyCode.UP && direction!=Direction.DOWN){
            snake.setCurrentDirection(Direction.UP);
        }else if(keyCode==KeyCode.DOWN && direction!=Direction.UP){
            snake.setCurrentDirection(Direction.DOWN);
        }else if(keyCode==KeyCode.RIGHT && direction!=Direction.LEFT){
            snake.setCurrentDirection(Direction.RIGHT);
        }else if(keyCode==KeyCode.LEFT && direction!=Direction.RIGHT){
            snake.setCurrentDirection(Direction.LEFT);
        }
        snake.update();
    }
    public void checkCollision() throws InterruptedException {
        if(snake.isCollidingWithFood(food)){
            snake.grow();
            food.respawn();
        }
        if(snake.isDead()){
            running=false;
            System.out.println("hi");
            Pane pane = new Pane();
            Label label = new Label("GAME OVER");
            pane.getChildren().add(label);
            pane.getChildren().get(0).setTranslateX(130);
            pane.getChildren().get(0).setTranslateY(130);
            Launcher.stage.getScene().setRoot(pane);
        }
    }
    public void redraw(){
        platform.render(snake,food);
    }
    @Override
    public void run() {
        while (running){
            update();
            try {
                checkCollision();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redraw();
            try{
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Platform getPlatform() {
        return this.platform;
    }
}
