package sample.tryDragAndDrop;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class launchDADList extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("DADList");
        ObservableList<String> list =  FXCollections.observableArrayList();
        list.addAll("java","Python","ruby","R","go");
        DADList sourceV = new DADList();
        DADList targetV = new DADList();
        sourceV.lv.getItems().addAll(list);
        GridPane pane = new GridPane();
        Label source = new Label("Source List: ");
        Label target = new Label("Target List: ");
        pane.addRow(1,source,target);
        pane.addRow(2,sourceV.lv,targetV.lv);
        VBox root=new VBox();
        root.getChildren().add(pane);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}
