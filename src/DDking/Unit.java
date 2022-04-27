package DDking;
import java.util.Random;
import java.io.*;
import DDking.Weapon;
import java.util.ArrayList;
import java.util.Scanner;
import DDking.MyUtility;
//Unit class
public class Unit {
    private String name;
    private int maxHp;
    private int curHp;
    private int avgAtk;
    private int level;
    
    private Random oRandom = new Random();
    
    
    public Unit() {name =""; maxHp=1; curHp=1; avgAtk = 0; level = 1;}
    
    public Unit(String name, int level) {
        this.name = name;
        this.level = level;
        this.maxHp = (int)(oRandom.nextDouble(1,5)*MyUtility.pow(2,(level/5)));
        curHp = maxHp;
        avgAtk = maxHp/5 + (int)(oRandom.nextDouble()*maxHp/5);
    }
    
    public void attack(Unit opponent) {
        opponent.curHp -= avgAtk*2*oRandom.nextGaussian();
    }
    
    public String showInfo() {
        String info = new String();
        info += name + "(Lv " + level + ")" + '\n' +
                "HP : " + curHp +" / " + maxHp + " | ATK : " + avgAtk + '\n';
        return info;
    }
    
    public boolean isAlive() {return curHp>0;}
    
    //getter
    public String getName() {return name;}
    public int getMaxHp() {return maxHp;}
    public int getCurHp() {return curHp;}
    public int getAvgAtk() {return avgAtk;}
    public int getLevel() {return level;}
    //setter
    public void setName(String newName) {this.name = newName;}
    public void setMaxHp(int newMaxHp) {this.maxHp = newMaxHp;}
    public void setCurHp(int newCurHp) {this.curHp = newCurHp;}
    public void setAvgAtk(int newAvgAtk) {this.avgAtk = newAvgAtk;}
    public void setLevel(int newLevel) {this.level = newLevel;}
}

//Hero class
class Hero extends Unit{
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>(); 
    private int gold;
    private int exp;
    private Scanner scanner = new Scanner(System.in);
    private Weapon weapon = new Weapon();
    
    public Hero() {super(); gold = 0; exp = 0; weapons.add(new Weapon());}
    public Hero(String name,int level) {
        super(name,level); gold = 0; exp = 0; weapons.add(new Weapon());
    }

    @Override
    public String showInfo() {
        String info = new String();
        info += getName() + "(Lv " + getLevel() + ")" + '\n' + "HP : " + getCurHp() + " / " + getMaxHp() + " | Gold : "
                + gold + " | Exp : " + exp + '\n' + "Weapon : " + weapon.showInfo();
        return info;
    }
    
    public String WeaponList() {
        String list = new String();
        int i = 1;
        for (Weapon w : weapons) {
            list += i++ + ". " + w.showInfo();
        }
        return list;
    }
    
    public void changeWeapon() {
        System.out.println("Hero's Weapons");
        System.out.println("--------------------------------------");
        System.out.println(WeaponList());
        System.out.println("--------------------------------------");
        System.out.println("Select Weapons : ");
        while (true) {
            int n = scanner.nextInt();
            if (0 < n && n <= weapons.size()) {
                this.weapon = weapons.get(n);
                this.setAvgAtk(weapons.get(n).getDamage());
                System.out.print(showInfo());
                break;
            } else
                System.out.println("Put Correct Number");
        }
    }

    public void kill(Enemy e) {
        exp += e.getLevel();
        gold += 100;
    }
    
    public void levelUp() {
        exp = 0;
        setLevel(getLevel() + 1);
    }
    
    public void drawWeapon(String type) {
        gold -= 300;
        Weapon w = new Weapon(type, getLevel());
        System.out.println("New Weapon!" + '\n' + w.showInfo());
        weapons.add(w);
    }
    
    public void goHospital() {
        if (gold >= 100) {
            gold -= 100;
            setCurHp(getMaxHp());
            System.out.println("치료가 완료되었습니다!");
            this.showInfo();
        } else
            System.out.println("돈이 부족합니다.");
    }
    
    //getter
    public int getGold() {return gold;}
    public int getExp() {return exp;}
    //setter
    public void setGold(int newGold) {gold = newGold;}
    public void setExp(int newExp) {exp = newExp;}
}

//Enemy class
class Enemy extends Unit{
    Enemy(){
        super();
//        try {
//            this.setName(MyUtility.randomEnemyName());
//        }
//        catch (FileNotFoundException ex) {
//            
//        }
//        catch(IOException ex) {
//            
//        }
    }
    
}