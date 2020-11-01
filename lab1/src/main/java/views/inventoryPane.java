package views;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.item.basedEquipment;
import javafx.scene.control.ScrollPane;
import java.util.ArrayList;
import static controller.AllCustomHandler.genHero.onDragDetected;
import static controller.AllCustomHandler.genHero.onEquipDone;

public class inventoryPane extends ScrollPane {
    private ArrayList<basedEquipment> equipmentArr;
    public inventoryPane() {

    }
    private Pane getDetailsPane() {
        Pane infoPane = new HBox(10);
        infoPane.setBorder(null);
        infoPane.setPadding(new Insets(25, 25, 25, 25));
        if (equipmentArr != null) {
            ImageView[] imgV = new ImageView[equipmentArr.size()];
            for (int i = 0; i < equipmentArr.size(); i++) {
                imgV[i] = new ImageView();
                imgV[i].setImage(new Image(getClass().getClassLoader().getResource(equipmentArr.get(i).getImgPath()).toString()));
                int keep =i;
                imgV[i].setOnDragDetected((event)->{
                    onDragDetected(event,equipmentArr.get(keep),imgV[keep]);
                });
                imgV[i].setOnDragDone((event)->{
                    onEquipDone(event);
                });
            }
            infoPane.getChildren().addAll(imgV);
        }
        return infoPane;
    }

        public void drawPane(ArrayList<basedEquipment> equipments){
            this.equipmentArr = equipments;
            Pane infoPane = getDetailsPane();
            this.setStyle("-fx-background-color: Red;");
            this.setContent(infoPane);
        }
    }


