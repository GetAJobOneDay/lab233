package controller;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Food;
import model.Snake;
import view.Platform;

public class Launcher extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        Launcher.stage =stage;
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(Platform.WIDTH/2,Platform.HEIGHT/2));
        Food food = new Food();
        GameLoop gameLoop = new GameLoop(platform,snake,food);
        Scene scene = new Scene(platform,Platform.WIDTH*Platform.TILE_SIZE,Platform.HEIGHT*Platform.TILE_SIZE);
        scene.setOnKeyPressed(e->platform.setKey(e.getCode()));
        scene.setOnKeyReleased(e->platform.setKey(null));
        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        (new Thread(gameLoop)).start();
    }
    public static void main(String[] args){
        launch(args);
    }
}
