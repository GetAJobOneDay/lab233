package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import model.Charecter.basedChar;
import model.item.Armor;
import model.item.Weapon;
import model.item.basedEquipment;
import java.util.ArrayList;
public  class AllCustomHandler  {
    public static class genHero implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent e){
            launcher.setMainChar(genCharacter.setUpChar());
            launcher.setEquipArmor(null);
            launcher.setEquipWeapon(null);
            ArrayList<basedEquipment> arr= genItemList.setUpItem();
            launcher.setAllEquipment(arr);
            launcher.refreshPane();
        }
        public static void onDragDetected(MouseEvent e, basedEquipment beq, ImageView imgV){
            Dragboard db =imgV.startDragAndDrop(TransferMode.ANY);
            db.setDragView(imgV.getImage());
            ClipboardContent content = new ClipboardContent();
            content.put(beq.DATA_FORMAT,beq);
            db.setContent(content);
            e.consume();
        }
        public static void onDragOver(DragEvent event, String type){
            Dragboard db = event.getDragboard();
            basedEquipment retrievedEq =(basedEquipment) db.getContent(basedEquipment.DATA_FORMAT);
            if(db.hasContent(basedEquipment.DATA_FORMAT)&&retrievedEq.getClass().getSimpleName().equals(type)){
                event.acceptTransferModes(TransferMode.MOVE);
            }
        }
        public static void onDragDropped(DragEvent event, Label label, StackPane pane){
            basedChar mainChar =launcher.getMainChar();
            boolean isComplete=false;
            ArrayList<basedEquipment> alleq = launcher.getAllEquipment();
            Dragboard db =event.getDragboard();
            if(db.hasContent(basedEquipment.DATA_FORMAT)){
                basedEquipment retrieveEq = (basedEquipment) db.getContent(basedEquipment.DATA_FORMAT);
                basedChar character =launcher.getMainChar();
                if(retrieveEq.getClass().getSimpleName().equals("Weapon") ){
                    if( mainChar.getClass().getSimpleName().equals("physicalCharacter")){
                        if(retrieveEq.getDamageType().toString().equals("physical")){
                            System.out.println(retrieveEq.getDamageType().toString());
                            if(launcher.getEquipWeapon()!=null){
                                alleq.add(launcher.getEquipWeapon());
                            }
                            launcher.setEquipWeapon((Weapon) retrieveEq);
                            character.equipWeapon((Weapon) retrieveEq);
                        }
                    }else if(mainChar.getClass().getSimpleName().equals("magicalCharacter")){
                        if(retrieveEq.getDamageType().toString().equals("magical")){
                            System.out.println(retrieveEq.getDamageType().toString());
                            if(launcher.getEquipWeapon()!=null){
                                alleq.add(launcher.getEquipWeapon());
                            }
                            launcher.setEquipWeapon((Weapon) retrieveEq);
                            character.equipWeapon((Weapon) retrieveEq);
                        }
                    }else if(mainChar.getClass().getSimpleName().equals("BattleMage")){
                        if(retrieveEq.getDamageType().toString().equals("SPD")){
                            System.out.println(retrieveEq.getDamageType().toString());
                            if(launcher.getEquipWeapon()!=null){
                                alleq.add(launcher.getEquipWeapon());
                            }
                            launcher.setEquipWeapon((Weapon) retrieveEq);
                            character.equipWeapon((Weapon) retrieveEq);
                        }
                    }
                }else if(retrieveEq.getClass().getSimpleName().equals("Armor")){
                    if(!mainChar.getClass().getSimpleName().equals("BattleMage")){
                        if(launcher.getEquipArmor()!=null){
                            alleq.add(launcher.getEquipArmor());

                        }
                        launcher.setEquipArmor((Armor) retrieveEq);
                        character.equipArmor((Armor) retrieveEq);
                    }

                }
                launcher.setMainChar(character);
                launcher.setAllEquipment(alleq);
                launcher.refreshPane();
                ImageView imgv = new ImageView();
                if(pane.getChildren().size()!=1){
                    pane.getChildren().remove(1);
                    launcher.refreshPane();
                }
                label.setText(retrieveEq.getClass().getSimpleName()+":\n"+retrieveEq.getName());
                imgv.setImage(new Image(AllCustomHandler.class.getClassLoader().getResource(retrieveEq.getImgPath()).toString()));
                pane.getChildren().add(imgv);
                isComplete=true;
            }
            event.setDropCompleted(isComplete);
        }
        public static void onEquipDone(DragEvent event){
            Dragboard db = event.getDragboard();
            ArrayList<basedEquipment> alleq = launcher.getAllEquipment();
            basedEquipment retreieveEq = (basedEquipment) db.getContent(basedEquipment.DATA_FORMAT);
            int pos=-1;
            for(int i=0;i<alleq.size();i++){
                if(alleq.get(i).getName().equals(retreieveEq.getName())){
                    pos=i;
                }
            }
            if(pos!=-1){
                alleq.remove(pos);
            }
            launcher.setAllEquipment(alleq);
            launcher.refreshPane();
        }
        public static void setToNormal(String x ,String y){
            ArrayList<basedEquipment> alleq = launcher.getAllEquipment();
            int pos=-1;
            for(int i=0;i<alleq.size();i++){
                if(alleq.get(i).getName().equals(x)){
                    pos=i;
                }
            }
            if(pos!=-1){
                alleq.remove(pos);
            }
            launcher.setAllEquipment(alleq);
            launcher.refreshPane();
        }
    }
}
