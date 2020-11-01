package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Charecter.basedChar;
import model.item.Armor;
import model.item.Weapon;
import model.item.basedEquipment;
import views.EquipPane;
import views.charPane;
import views.inventoryPane;

import java.util.ArrayList;

public class launcher extends Application {
    private static Scene mainScene;
    private static basedChar mainChar=null;
    private static ArrayList<basedEquipment> equipments = null;
    public static ArrayList<basedEquipment> fullEq = genItemList.setUpItem();
    private static Weapon weapon =null;
    private static Armor armor =null;
    private static charPane characterPane=null;
    private static EquipPane equipPane =null;
    private static inventoryPane invenPane=null;

    public static basedEquipment getEquipWeapon() {
        return launcher.weapon;
    }

    public static void setEquipWeapon(Weapon retrieveEq) {
        launcher.weapon =retrieveEq;
    }

    public static basedEquipment getEquipArmor() {
        return launcher.armor;
    }

    public static void setEquipArmor(Armor retrieveEq) {
        launcher.armor = retrieveEq;
    }
    public static void setAllEquipment(ArrayList<basedEquipment> alleq) {
        launcher.equipments =alleq;
    }

    public static ArrayList<basedEquipment> getAllEquipment() {
        return launcher.equipments;
    }


    @Override
    public void start(Stage primaryStage)throws Exception{
        primaryStage.setTitle("intro to RPG");
        primaryStage.setResizable(false);
        Pane mainPane  = getMainPane();
        mainScene = new Scene(mainPane);
        mainChar = genCharacter.setUpChar();
        equipments = genItemList.setUpItem();
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
    public Pane getMainPane(){
        BorderPane mainPane = new BorderPane();
        characterPane = new charPane();
        equipPane = new EquipPane();
        invenPane = new inventoryPane();
        refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(invenPane);
        return mainPane;
    }
    public static void clearPane(){
        launcher.armor =null;
        launcher.weapon = null;
        invenPane.drawPane(fullEq);
        equipPane.drawPane(weapon,armor);
        characterPane.drawPane(mainChar);
        //equipPane.drawPane(null,null);
    }
    public static void refreshPane(){
        characterPane.drawPane(mainChar);
        equipPane.drawPane(weapon,armor);
        invenPane.drawPane(equipments);
    }
    public static basedChar getMainChar(){return mainChar;}
    public static void setMainChar(basedChar character){launcher.mainChar=character; }
    public static void main(String[] args){
        launch(args);
    }
}

