package model.item;

import javafx.scene.input.DataFormat;
import model.Charecter.DamageType;

import java.io.Serializable;


public class basedEquipment implements Serializable {
    public static final DataFormat DATA_FORMAT = new DataFormat("src.model.item.basedEquipment");
    protected String name;
    protected DamageType damageType;
    protected String imgPath;
    public String getName(){return name;}
    public String getImgPath(){return imgPath;}
    public DamageType getDamageType(){return damageType;}
    public void setImgPath(String imgPath){
        this.imgPath =imgPath;
    }
}
