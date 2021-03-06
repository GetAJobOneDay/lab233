package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Character;
import model.Keys;

public class Platform extends Pane {
    private Keys key;
    public static final int WIDTH = 800;
    public static final int HEIGHT =400;
    public static final int GROUND = 300;
    private Image platformImg;
    private Character character;
    public Platform(){
        key = new Keys();
        platformImg=new Image(getClass().getResourceAsStream("/assets/Background.png"));
        ImageView background= new ImageView(platformImg);
        background.setFitHeight(HEIGHT);
        background.setFitWidth(WIDTH);
        character = new Character(30,30,0,0, KeyCode.A,KeyCode.D,KeyCode.W);
        getChildren().addAll(background,character);
    }
    public Character getCharacter(){return character;}
    public Keys getKey(){return key;}
}
