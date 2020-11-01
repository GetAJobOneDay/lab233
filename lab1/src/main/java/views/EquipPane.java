package views;

import controller.AllCustomHandler;
import controller.genItemList;
import controller.launcher;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.Charecter.basedChar;
import model.item.Armor;
import model.item.Weapon;
import model.item.basedEquipment;

import java.util.ArrayList;

import static controller.AllCustomHandler.genHero.onDragDropped;
import static controller.AllCustomHandler.genHero.onDragOver;


public class EquipPane extends ScrollPane {
    private Weapon weapon;
    private Armor armor;
    public EquipPane(){

    }
    //1.4.3
    /*private Pane getDetailsPane(){
        Pane equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        ((VBox) equipmentInfoPane).setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(25,25,25,25));
        Label weaponLbl, armorLbl;
        ImageView weaponImg = new ImageView();
        ImageView armorImg = new ImageView();
        if (weapon != null){
            weaponLbl = new Label("Weapon: \n" + weapon.getName());
            weaponImg.setImage(new Image(getClass().getClassLoader().getResource(weapon.getImagePath()).toString()));
        }
        else {
            weaponLbl = new Label("Weapon:");
            weaponImg.setImage(new Image(getClass().getClassLoader().getResource("assets/blank.png").toString()));
        }
        if (armor != null){
            armorLbl = new Label("Armor: \n" + armor.getName());
            armorImg.setImage(new Image(getClass().getClassLoader().getResource(armor.getImagePath()).toString()));
        }
        else {
            armorLbl = new Label("Armor:");
            armorImg.setImage(new Image(getClass().getClassLoader().getResource("assets/blank.png").toString()));
        }
        equipmentInfoPane.getChildren().addAll(weaponLbl, weaponImg, armorLbl, armorImg);
        return equipmentInfoPane;
    }
    public void drawPane(Weapon equippedWeapon, Armor equippedArmor){
        this.armor = equippedArmor;
        this.weapon = equippedWeapon;
        Pane equipmentInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(equipmentInfo);
    }*/
    private Pane getDetailsPane(){
        Pane infoPane = new VBox(10);
        infoPane.setBorder(null);
        ((VBox) infoPane).setAlignment(Pos.CENTER);
        infoPane.setPadding(new Insets(25,25,25,25));
        Label weaponLb,armorLb;
        StackPane waeponImgGroup = new StackPane();
        StackPane armorImgGroup = new StackPane();
        ImageView bg1 = new ImageView();
        ImageView bg2 = new ImageView();
        ImageView weaponimg = new ImageView();
        ImageView armorimg = new ImageView();
        ImageView spdImg = new ImageView();
        bg1.setImage(new Image(getClass().getClassLoader().getResource("assets/blank.png").toString()));
        bg2.setImage(new Image(getClass().getClassLoader().getResource("assets/blank.png").toString()));
        waeponImgGroup.getChildren().add(bg1);
        armorImgGroup.getChildren().add(bg2);
        //spdImgGroup.getChildren().add()
        if(weapon!=null){
            weaponLb = new Label("Weapon :\n"+weapon.getName());
            weaponimg.setImage(new Image(getClass().getClassLoader().getResource(weapon.getImgPath()).toString()));
            waeponImgGroup.getChildren().add(weaponimg);
        }else{
            weaponLb = new Label("Weapon: ");
            weaponimg.setImage(new Image(getClass().getClassLoader().getResource("assets/blank.png").toString()));
        }
        if(armor!=null){
            armorLb = new Label("Armor :\n"+armor.getName());
            armorimg.setImage(new Image(getClass().getClassLoader().getResource(armor.getImgPath()).toString()));
            armorImgGroup.getChildren().add(armorimg);
        }else{
            armorLb = new Label("Armor: ");
            armorimg.setImage(new Image(getClass().getClassLoader().getResource("assets/blank.png").toString()));
        }
        waeponImgGroup.setOnDragOver((event)->{
            onDragOver(event,"Weapon");
        });
        armorImgGroup.setOnDragOver((event)->{
            onDragOver(event,"Armor");
        });
        waeponImgGroup.setOnDragDropped((event)->{
            onDragDropped(event,weaponLb,waeponImgGroup);
        });
        armorImgGroup.setOnDragDropped((event)->{
            onDragDropped(event,armorLb,armorImgGroup);
        });
        Button btn = new Button();
        btn.setText("UnEquip");
        btn.setOnAction((event)->{
            basedChar character = launcher.getMainChar();
            if(waeponImgGroup.getChildren().size()>1){
                waeponImgGroup.getChildren().remove(1);
                character.unEqWeapon();
                weapon=null;

            }
            if(armorImgGroup.getChildren().size()>1){
                armorImgGroup.getChildren().remove(1);
                character.unEqArmor();
                armor=null;
            }
            //charPane cp = new charPane();
            ArrayList<basedEquipment> arr= genItemList.setUpItem();
            launcher.setAllEquipment(arr);
            //cp.drawPane(character);
            launcher.clearPane();
        });
        infoPane.getChildren().addAll(weaponLb,waeponImgGroup,armorLb,armorImgGroup,btn);
        return infoPane;
    }
    public void drawPane(Weapon x ,Armor y){
        this.weapon =x;
        this.armor =y;
        Pane infoPane =getDetailsPane();
        this.setStyle("-fx-background-color: Red");
        this.setContent(infoPane);
    }
}
