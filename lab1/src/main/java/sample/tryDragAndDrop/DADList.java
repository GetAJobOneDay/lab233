package sample.tryDragAndDrop;


import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DADList {
    ListView<String> lv;
    public DADList(){
        lv = new ListView<>();
        lv.setPrefSize(200,200);
        lv.setOnDragDetected((event)->{
            Dragboard dragboard = lv.startDragAndDrop(TransferMode.MOVE);
            String select = lv.getSelectionModel().getSelectedItem();
            ClipboardContent content = new ClipboardContent();
            content.putString(select);
            dragboard.setContent(content);
        });
        lv.setOnDragOver((event)->{
            Dragboard db = event.getDragboard();
            if(event.getGestureSource()!= lv && db.hasString()){
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
        lv.setOnDragDropped((event)->{
            boolean isComplete = false;
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String list = db.getString();
                lv.getItems().addAll(list);
                isComplete = true;
            }
            event.setDropCompleted(isComplete);
        });
        lv.setOnDragDone((DragEvent event)->{
            lv.getItems().remove(lv.getSelectionModel().getSelectedItem());
        });
    }
}
