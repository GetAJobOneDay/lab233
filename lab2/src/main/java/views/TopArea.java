package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


import java.util.concurrent.Callable;

public class TopArea implements Callable<Pane> {
    private Button watch;
    private Button delete;
    private Button unWatch;
    public TopArea(Button watch,Button delete, Button unWatch){
        this.delete=delete;
        this.unWatch=unWatch;
        this.watch=watch;
    }
    @Override
    public HBox call() throws Exception {
        HBox topArea = new HBox(10);
        topArea.setPadding(new Insets(5));
        topArea.getChildren().addAll(watch,unWatch,delete);
        ((HBox) topArea).setAlignment(Pos.CENTER_RIGHT);
        return topArea;
    }
}
