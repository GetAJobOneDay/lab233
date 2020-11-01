package view;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import model.Food;
import model.Snake;

import java.util.Random;


public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;
    private Canvas canvas;
    private static KeyCode keyCode;
    public Platform(){
        this.setHeight(TILE_SIZE*HEIGHT);
        this.setWidth(TILE_SIZE*WIDTH);
        canvas = new Canvas(TILE_SIZE*WIDTH,TILE_SIZE*HEIGHT);
        this.getChildren().add(canvas);

    }
    public void render(Snake snake, Food food){
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0,WIDTH*TILE_SIZE,HEIGHT*TILE_SIZE);
        graphicsContext.setFill(Color.BLUE);
        snake.getBody().forEach(part->{
            graphicsContext.fillRect(part.getX()*TILE_SIZE,part.getY()*TILE_SIZE,TILE_SIZE,TILE_SIZE);
        });
        if(food.getType()!=1){
            graphicsContext.setFill(Color.RED);
        }else{
            graphicsContext.setFill(Color.GREEN);
        }
        graphicsContext.fillRect(food.getPosition().getX()*TILE_SIZE,food.getPosition().getY()*TILE_SIZE,TILE_SIZE,TILE_SIZE);


    }
    public static KeyCode getKeyCode(){
        return keyCode;
    }
    public void setKey(KeyCode key){
        keyCode =key;
    }
}
