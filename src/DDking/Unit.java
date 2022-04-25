package DDking;
import java.util.Random;
import DDking.Weapon;
import java.util.ArrayList;

public class Unit {
    private String name;
    private int hp;
    private int avgAtk;
    private int level;
    private Random oRandom = new Random();
    static int pow(int a,int b) {
        if (b==0) return 1;
        int ret = pow(a,b/2);
        if(b%2==0) return ret*ret;
        else return a*ret*ret;
    }
    
    public Unit() {name =""; hp=1; avgAtk = 0; level = 1;}
    public Unit(String name, int level) {
        this.name = name;
        this.level = level;
        this.hp = (int)(oRandom.nextDouble(1,5)*pow(2,(level/5)));
        avgAtk = hp/5 + (int)(oRandom.nextDouble()*hp/5);
    }
    
    public void attack(Unit opponent) {
        opponent.hp -= avgAtk*2*oRandom.nextGaussian();
    }
    public String showInfo() {
        String info = new String();
        info += name + "(Lv " + level + ")" + '\n' +
                "HP : " + hp + " | ATK : " + avgAtk + '\n';
        return info;
    }
    public boolean isAlive() {return hp>0;}
    
    //getter
    public String getName() {return name;}
    public int getHp() {return hp;}
    public int getAvgAtk() {return avgAtk;}
    public int getLevel() {return level;}
    //setter
    public void setName(String newName) {this.name = newName;}
    public void setHp(String newHp) {this.name = newHp;}
    public void setAvgAtk(String newAvgAtk) {this.name = newAvgAtk;}
    public void setLevel(String newLevel) {this.name = newLevel;}
}
class Hero extends Unit{
    ArrayList<Weapon> weapons = new ArrayList<Weapon>(); 
    private int gold;
    private int exp;
}
class Enemy extends Unit{
    
}