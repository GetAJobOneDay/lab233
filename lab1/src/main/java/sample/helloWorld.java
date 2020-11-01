package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
public class helloWorld extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FlowPane pane = new FlowPane();
        StackPane circle = createMonitorCircle();
        StackPane circle2 = createMonitorCircle();
        pane.getChildren().addAll(circle,circle2);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("customized event");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
private StackPane createMonitorCircle(){
        StackPane pane = new StackPane();
        VBox layout = new VBox();
        Label label = new Label("out side circle");
        Circle circle = new Circle(30);
        circle.setOnMouseMoved((event)->{
            label.setText("(x: "+event.getX()+", y: "+event.getY()+" )");
        });
        circle.setOnMouseExited((event)->{
            label.setText("outside circle");
        });
        layout.getChildren().addAll(circle,label);
        pane.getChildren().add(layout);
        return pane;
}

    public static void main(String[] args) {
        launch(args);
    }
}
