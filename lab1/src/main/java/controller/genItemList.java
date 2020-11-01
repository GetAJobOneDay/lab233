package controller;

import model.Charecter.DamageType;
import model.item.Armor;
import model.item.Weapon;
import model.item.basedEquipment;

import java.util.ArrayList;

public class genItemList {
    /*public static basedEquipment[] setUpItem() {
        basedEquipment[] item = new basedEquipment[5];
        item[0] = new Weapon("sword", 10, DamageType.physical, "assets/sword1.png");
        item[1] = new Weapon("gun", 20, DamageType.physical, "assets/gun1.png");
        item[2] = new Weapon("holy sword", 30, DamageType.physical, "assets/staff1.png");
        item[3] = new Armor("shirt", 2, 50, "assets/shirt1.png");
        item[4] = new Armor("armor", 10, 5, "assets/armor1.png");*/
        public static ArrayList<basedEquipment> setUpItem(){
            ArrayList<basedEquipment> item = new ArrayList<basedEquipment>();
            item.add(new Weapon("sword",10, DamageType.physical,"assets/sword1.png"));
            item.add(new Weapon("gun",20, DamageType.physical,"assets/gun1.png"));
            item.add(new Weapon("staff",30, DamageType.magical,"assets/staff1.png"));
            item.add(new Armor("shirt",2, 50,"assets/shirt1.png"));
            item.add(new Armor("armor",10, 5,"assets/armor1.png"));
            item.add(new Weapon("holySaber",45,DamageType.SPD,"assets/holySaber.png"));
            return item;
    }
}
