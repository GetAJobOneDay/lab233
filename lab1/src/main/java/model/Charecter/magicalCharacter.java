package model.Charecter;

public class magicalCharacter extends basedChar {
    public magicalCharacter(String name,String imgPath,int baseDef,int baseRes,int speed){
        this.speed = speed;
        this.name = name;
        this.type = DamageType.magical;
        this.imgPath = imgPath;
        this.fullHp = 100;
        this.basePow = 30;
        this.baseDef =baseDef;
        this.basedRes = baseRes;
        this.hp = this.fullHp;
        this.power = this.basePow;
        this.defense = baseDef;
        this.resistance = basedRes;
    }
}