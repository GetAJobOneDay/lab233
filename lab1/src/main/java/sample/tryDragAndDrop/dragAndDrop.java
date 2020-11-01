package sample.tryDragAndDrop;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class dragAndDrop {
    private StackPane pane;
    private double origin_x=80,origin_y=50;
    public dragAndDrop(int x, int y, int radius, Color color, String txt){
        Circle circle = new Circle(radius,color);
        Text text = new Text(txt);
        this.pane = new StackPane(circle,text);
        this.pane.setLayoutX(x);
        this.pane.setLayoutY(y);
    }
    public void setBackOrigin(){
        this.pane.setLayoutX(origin_x);
        this.pane.setLayoutY(origin_y);
    }
    public void setPosition(double x, double y){
        this.pane.setLayoutX(this.pane.getLayoutX()+x);
        this.pane.setLayoutY(this.pane.getLayoutY()+y);
    }
    public StackPane getPane(){return this.pane;}
}
