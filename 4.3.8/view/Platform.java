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
    private Character rockman;
    public Platform(){
        key = new Keys();
        platformImg=new Image(getClass().getResourceAsStream("/assets/Background.png"));
        ImageView background= new ImageView(platformImg);
        background.setFitHeight(HEIGHT);
        background.setFitWidth(WIDTH);
        character = new Character(30,30,0,0, KeyCode.A,KeyCode.D,KeyCode.W,"MarioSheet",32,16);
        rockman = new Character(30,4,0,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,"rockmanSheet",65,75);
        getChildren().addAll(background,character,rockman);
    }
    public Character getCharacter(){return this.character;}
    public Character getRockman(){return rockman;}
    public Keys getKey(){return this.key;}
}
