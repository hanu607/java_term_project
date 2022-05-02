package DDking;

import java.util.Random;
import java.io.File;
import DDking.MyUtility;

public class Weapon {
    private String name;
    private String type;
    private int ATK;
    private int level;
    private Random oRandom = new Random();

    public Weapon() {
        name = "Spoon";
        type = "";
        ATK = 1;
        level = 0;
    }

    public Weapon(int level) {
        try {
            String newFirstName = MyUtility.randomName("Weapon(first name).txt");
            name = newFirstName;
        } catch (Exception ex) {
            System.out.println("no match file : Weapon(first name).txt");
            System.exit(0);
        }

        try {
            String newSecondName = MyUtility.randomName("Weapon(second name).txt");
            name += " " + newSecondName;
        } catch (Exception ex) {
            System.out.println("no match file : Weapon(second name).txt");
            System.exit(0);
        }

        try {
            String newType = MyUtility.randomName("Weapon(type name).txt");
            name += " " + newType;
        } catch (Exception ex) {
            System.out.println("no match file : Weapon(type name).txt");
            System.exit(0);
        }
        this.level = level;
        this.ATK = (int) (oRandom.nextDouble(level/2, 2.5) * MyUtility.pow(2, level)/5);
    }

    public String IconNameLv() {
        String s = "|" + name + "(Lv " + level + ")|";
        return s;
    }

    public String IconATK() {
        String s = "|ATK : " + ATK + "|";
        return s;
    }

    public String showInfo() {
        String info = new String();
        info += IconNameLv() + IconATK();
        return info;
    }
    //getter
    public String getName() {return name;}
    public String getType() {return type;}
    public int getATK() {return ATK;}
    public int getLevel() {return level;}
    //setter
    public void setName(String newName) {name = newName;}
    public void setType(String newType) {type = newType;}
    public void setATK(int newATK) {ATK = newATK;}
    public void setLevel(int newLevel) {level = newLevel;}
}
