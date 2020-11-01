package model.Charecter;

public class BattleMage extends basedChar {
    public BattleMage(String name,String imgPath,int baseDef,int baseRes, int spped){
        this.speed =spped;
        this.name = name;
        this.type = DamageType.SPD;
        this.imgPath = imgPath;
        this.fullHp = 40;
        this.basePow = 40;
        this.baseDef =baseDef;
        this.basedRes = baseRes;
        this.hp = this.fullHp;
        this.power = this.basePow;
        this.defense = baseDef;
        this.resistance = basedRes;
    }
}
