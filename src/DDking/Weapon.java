package DDking;
import java.util.Random;
import java.io.File;

public class Weapon {
    private String name;
    private String type;
    private int dmg;
    private int level;
    private Random oRandom = new Random();
    
    public static void main(String[]args) {
        File file = new File("Weapon (first name).txt");
        if(file.exists()) System.out.println("exist");
        else System.out.println("no");
        }
    static int pow(int a,int b) {
        if (b==0) return 1;
        int ret = pow(a,b/2);
        if(b%2==0) return ret*ret;
        else return a*ret*ret;
    }
    
    public Weapon() {name = ""; type = "useless"; dmg = 0; level = 1;}
    public Weapon(String type,int level) {
        this.type = type;
        this.name = name + type; 
        this.level = level;
        this.dmg = (int)(oRandom.nextDouble(1,2.5)*pow(2,(level/5)));
        } 
}
