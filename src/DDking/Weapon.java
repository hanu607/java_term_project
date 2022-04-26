package DDking;
import java.util.Random;
import java.io.File;

public class Weapon {
    private String name;
    private String type;
    private int damage;
    private int level;
    private int price;
    private Random oRandom = new Random();
    
   
    static int pow(int a,int b) {
        if (b==0) return 1;
        int ret = pow(a,b/2);
        if(b%2==0) return ret*ret;
        else return a*ret*ret;
    }
    
    public Weapon() {name = "Spoon"; type = ""; damage = 1; level = 0; price = 0;}
    public Weapon(String type,int level) {
        this.type = type;
        this.name = name + type; 
        this.level = level;
        this.damage = (int)(oRandom.nextDouble(1,2.5)*pow(2,(level/5)));
        this.price = level*10;
        } 
    public String showInfo() {
        String info = new String();
        info += name + "(Lv " + level + ") | ATK : " + damage + '\n';
        return info;
    }
    //getter
    public String getName() {return name;}
    public String getType() {return type;}
    public int getDamage() {return damage;}
    public int getLevel() {return level;}
    //setter
    public void setName(String newName) {name = newName;}
    public void setType(String newType) {type = newType;}
    public void setDamage(int newDamage) {damage = newDamage;}
    public void setLevel(int newLevel) {level = newLevel;}
}
