package model.item;

public class Armor extends basedEquipment {
    private int defence,resistance;
    public Armor(String name,int defence,int resistance,String imgPath){
        this.defence=defence;
        this.resistance=resistance;
        this.imgPath=imgPath;
        this.name=name;
    }
    public int getDefence(){return defence;}
    public int getResistance(){return resistance;}

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }
    public String getImagePath(){return imgPath;}
    @Override
    public String toString(){return name;}
}
