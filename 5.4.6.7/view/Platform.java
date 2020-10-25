package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Character;
import model.Keys;

import java.util.ArrayList;

public class Platform extends Pane {
    private final Keys key;
    public static final int WIDTH = 800;
    public static final int HEIGHT =400;
    public static final int GROUND = 300;
    private final Image platformImg;
    private final ArrayList<Character> allChar ;
    private final ArrayList<Score> score;
    public Platform(){
        allChar = new ArrayList<>();
        score = new ArrayList<>();
        key = new Keys();
        platformImg=new Image(getClass().getResourceAsStream("/assets/Background.png"));
        ImageView background= new ImageView(platformImg);
        background.setFitHeight(HEIGHT);
        background.setFitWidth(WIDTH);
        Character mario = new Character(390,200,0,0, KeyCode.A,KeyCode.D,KeyCode.W,"MarioSheet",64,32);
        Character rockman = new Character(390,225,0,0, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,"rockmanSheet",65,75);
        score.add(new Score(30,GROUND+30));
        score.add(new Score(Platform.WIDTH-60,GROUND+30));
        allChar.add(mario);
        allChar.add(rockman);
        getChildren().addAll(background,mario,rockman);
        getChildren().addAll(score);
    }
    public ArrayList<Character> getCharacter(){return this.allChar;}
    public Keys getKey(){return this.key;}

    public ArrayList<Score> getScore() {
        return score;
    }

    public Keys getKeys() {
        return this.key;
    }
}
