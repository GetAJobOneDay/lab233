package model.item;

import model.Charecter.DamageType;

public class Weapon extends basedEquipment{
    private int power;
    //private DamageType damageType;
    public Weapon(String name, int power, DamageType damageType, String imgPath){
        this.name =name;
        this.imgPath = imgPath;
        this.damageType = damageType;
        this.power =power;
    }
    public int getPower(){return power;}
    public DamageType getDamageType(){return damageType;}
    public void setPower(int power){this.power=power;}
    public void setDamageType(DamageType damageType){
        this.damageType =damageType;
    }
    public String getImagePath(){return imgPath;}
    @Override
    public String toString(){return name;}
}
