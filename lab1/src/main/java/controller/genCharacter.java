package controller;

import model.Charecter.BattleMage;
import model.Charecter.basedChar;
import model.Charecter.magicalCharacter;
import model.Charecter.physicalCharacter;

import java.util.Random;

public class genCharacter {
    public static basedChar setUpChar(){
        basedChar character;
        Random rand = new Random();
        int type= rand.nextInt(3)+1;
        int baseDef = rand.nextInt(50)+1;
        int baseRes = rand.nextInt(50)+1;
        int speed= rand.nextInt(50)+1;
        if(type==1){
            character =new magicalCharacter("magicalCharacter1","assets/wizard.png",baseDef,baseRes,speed);
        }else if(type==2){
            character = new physicalCharacter("physicalCharacter","assets/knight.png",baseDef,baseRes,speed);
        }else{
            character = new BattleMage("BattleMage","assets/battleMage.png",baseDef,baseRes,speed);
        }
        return character;
    }
}
