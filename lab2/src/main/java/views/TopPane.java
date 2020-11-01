package views;

import controller.AllEventHandlers;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.time.LocalDateTime;

public class TopPane extends FlowPane {
    private Button refresh;
    private Button add;
    private Label update;
    public TopPane(){
        this.setPadding(new Insets(10));
        this.setHgap(10);
        this.setPrefSize(640,20);
        add = new Button("ADD");
        refresh =new Button("refresh");
        refresh.setOnAction(event -> AllEventHandlers.onRefresh());
        add.setOnAction(event -> AllEventHandlers.onAdd());
        update = new Label();
        refreshPane();
        this.getChildren().addAll(refresh,add,update);
    }
    public void refreshPane(){
        update.setText(String.format("Last update: %s", LocalDateTime.now().toString()));
    }
}
