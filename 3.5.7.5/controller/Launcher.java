package controller;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Launcher extends Application {
    public static Stage primaryStage;
    public static HostServices hs;
    @Override
    public void start(Stage primaryStage)throws Exception{
        Launcher.primaryStage = primaryStage;
        hs =getHostServices();
        Pane mainPane = FXMLLoader.load(getClass().getResource("../view/mainView.fxml"));
        Launcher.primaryStage.setTitle("indexer");
        Launcher.primaryStage.setScene(new Scene(mainPane));
        Launcher.primaryStage.setResizable(false);
        Launcher.primaryStage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}
