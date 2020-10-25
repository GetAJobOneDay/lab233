package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Platform;


public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform platform = new Platform();
        GameLoop gameLoop =  new GameLoop(platform);
        DrawingLoop drawingLoop = new DrawingLoop(platform);
        Scene scene = new Scene(platform,platform.WIDTH,platform.HEIGHT);
        scene.setOnKeyPressed(e->platform.getKey().add(e.getCode()));
        scene.setOnKeyReleased(e->platform.getKey().remove(e.getCode()));
        primaryStage.setTitle("platform");
        primaryStage.setScene(scene);
        primaryStage.show();
        (new Thread(gameLoop)).start();
        (new Thread(drawingLoop)).start();
    }
    public static void main(String[] args){
        launch(args);
    }
}
