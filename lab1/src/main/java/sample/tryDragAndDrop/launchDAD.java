package sample.tryDragAndDrop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.tryDragAndDrop.dragAndDrop;

public class launchDAD extends Application {
    double mouse_x,mouse_y;
    boolean isEnter = false;
    @Override
    public  void start(Stage primaryStage)throws Exception{
        primaryStage.setTitle("try drag and drop");
        Group root= new Group();
        dragAndDrop source = new dragAndDrop(80,50,40,Color.LAVENDERBLUSH,"SOURCE");
        dragAndDrop target = new dragAndDrop(350,50,60,Color.LIGHTCORAL,"TARGET");
        /*Button btn = new Button();
        btn.setText("ReStart");
        btn.setLayoutX(480);
        btn.setLayoutY(180);
        btn.setOnAction((event)->{
            source.setBackOrigin();
            source.getPane().setMouseTransparent(false);
        });*/
        source.getPane().setOnMousePressed((pressed)->{
            mouse_x = pressed.getSceneX();
            mouse_y = pressed.getScreenY();
        });
        source.getPane().setOnDragDetected((event)->{
            source.getPane().setMouseTransparent(true);
            source.getPane().toFront();
            source.getPane().startFullDrag();
        });
        source.getPane().setOnMouseDragged((mouseEvent)->{
            double Dx = mouseEvent.getScreenX()-mouse_x;
            double Dy = mouseEvent.getScreenY() - mouse_y;
            source.setPosition(Dx, Dy);
            mouse_x = mouseEvent.getScreenX();
            mouse_y =mouseEvent.getScreenY();
        });
        source.getPane().setOnMouseReleased((mouseEvent)->{
            if(isEnter){
                target.getPane().getChildren().add(source.getPane());
            }else{
                source.setPosition(mouseEvent.getX(),mouseEvent.getY());
                source.getPane().setMouseTransparent(false);
            }
        });
        target.getPane().setOnMouseDragEntered((mouseEvent)->{
            isEnter =true;
        });
        target.getPane().setOnMouseDragExited((mouseEvent)->{
            isEnter =false;
        });

        root.getChildren().addAll(source.getPane(),target.getPane());
        //root.getChildren().add(btn);
        Scene scene = new Scene(root,500,200);
        scene.setFill(Color.GRAY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void  main(String[] args){
        launch(args);
    }
}
