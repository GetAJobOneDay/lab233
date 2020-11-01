package views;
import controller.AllCustomHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Charecter.basedChar;
import javafx.scene.control.ScrollPane;


public class charPane extends ScrollPane {
    private basedChar character;
    public charPane(){

    }
    private Pane getDetailsPane(){
        Pane infoPane = new VBox(10);
        infoPane.setBorder(null);
        infoPane.setPadding(new Insets(25,25,25,25));
        Label name,type,hp,atk,def,res,speed;
        ImageView mainimg = new ImageView();
        if(this.character!=null){
            name = new Label("Name: "+character.getName());
            mainimg.setImage(new Image(getClass().getClassLoader().getResource(character.getImgPath()).toString()));
            hp =new Label("hp: "+character.getHp().toString()+"/"+character.getFullHp().toString());
            type = new Label("type: "+character.getType().toString());
            atk = new Label("atk: "+character.getPower());
            def = new Label("def: "+character.getDefense());
            res = new Label("res: "+character.getResistance());
            speed = new Label("SPEED :"+character.getSpeed());
        }else{
            name =new Label("name: ");
            mainimg.setImage(new Image(getClass().getClassLoader().getResource("/assets/unknown.png").toString()));
            hp =new Label("hp: ");
            type =new Label("type: ");
            atk = new Label("atk: ");
            def = new Label("def: ");
            res = new Label("res: ");
            speed = new Label("SPEED :");
        }
        Button generate= new Button();
        generate.setText("generate character");
        generate.setOnAction(new AllCustomHandler.genHero());
        infoPane.getChildren().addAll(name,mainimg,type,hp,atk,def,res,speed,generate);
        return infoPane;
    }
    public void drawPane(basedChar x){
        this.character =x;
        Pane infoPane = getDetailsPane();
        this.setStyle("-fx-background-color: Red");
        this.setContent(infoPane);
    }
}
