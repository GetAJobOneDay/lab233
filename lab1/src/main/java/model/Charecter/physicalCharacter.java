package model.Charecter;

public class physicalCharacter extends basedChar{
    public physicalCharacter(String name,String imgPath,int baseDef,int baseRes,int spped){
        this.speed =spped;
        this.name = name;
        this.type = DamageType.physical;
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
