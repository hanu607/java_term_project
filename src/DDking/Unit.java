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

    protected Random oRandom = new Random();

    public Unit() {
        name = "";
        maxHp = 1;
        curHp = 1;
        avgAtk = 1;
        level = 1;
    }

    public Unit(String name, int level) {
        this.name = name;
        this.level = level;
        this.maxHp = 1 + (int)(oRandom.nextDouble(level%5, 5) * MyUtility.pow(2, level)/5);
        curHp = maxHp;
        int temp = 1 + (int)(oRandom.nextDouble(level%5, 5) * MyUtility.pow(2, level)/5);
        avgAtk = temp / 5 + (int) (oRandom.nextGaussian() + temp / 5);
        if(avgAtk < 1) avgAtk = 1;
    }

    public void attack(Unit opponent) {
        int damage = 0;
        if (avgAtk == 1)
            damage = 1;
        else
            damage = (int)(avgAtk + oRandom.nextGaussian()) + 1;
        System.out.println(this.getName() + "의 공격 : " + damage + "피해");
        opponent.curHp -= damage;
    }

    // Icons
    public String IconNameLv() {
        String s = "|" + this.getName() + "(Lv " + this.getLevel() + ")" + "|";
        return s;
    }

    public String IconHp() {
        String s = "|HP : " + this.getCurHp() + "/" + this.getMaxHp() + "|";
        return s;
    }
    public String IconAvgAtk() {
        String s = "|AvgAtk : " + this.getAvgAtk() + "|";
        return s;
    }

    public String showInfo() {
        String info = new String();
        info += IconNameLv() + IconHp()+ IconAvgAtk();
        return info;
    }

    public boolean isAlive() {
        return curHp > 0;
    }

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
class Hero extends Unit {
    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private int gold;
    private int curExp;
    private int maxExp;
    private Scanner scanner = new Scanner(System.in);
    private Weapon weapon = new Weapon();

    public Hero() {
        super();
        setMaxHp(10);
        setCurHp(getMaxHp());
        gold = 0;
        curExp = 0;
        maxExp = 10;
        weapons.add(new Weapon());
    }

    public Hero(String name, int level) {
        super(name, level);
        gold = 0;
        curExp = 0;
        maxExp = 10 * level;
        weapons.add(new Weapon());
    }

    // Icons : | icon : value |

    public String IconGold() {
        String s = "|Gold : " + this.getGold() + "|";
        return s;
    }

    public String IconExp() {
        String s = "|Exp : " + this.getCurExp() + "/" + this.getMaxExp() + "|";
        return s;
    }

    public String IconWeapon() {
        String s = "[Weapon : " + this.getWeapon().showInfo() + "]";
        return s;
    }

    @Override
    public String showInfo() {
        String info = new String();
        info += IconNameLv() + '\n' + IconHp() + IconGold() + IconExp() + '\n' + IconWeapon();
        return info;
    }

    public String WeaponList() {
        String list = new String();
        list += "Weapon List\n" + "----------------------------\n"
                + "0. Escape\n";
        
        int i = 1;
        for (Weapon w : weapons) {
            list += i++ + ". " + w.showInfo() + '\n';
        }
        list += "----------------------------";
        return list;
    }

    public int changeWeapon() {
        int n = scanner.nextInt();
        while (0 > n || n > weapons.size()) {
            System.out.println("Put Correct Number");
            n = scanner.nextInt();
        }
        if (n != 0) {
            this.setAvgAtk(weapons.get(n - 1).getATK());
        }
        return n;
    }

    public void kill(Enemy e) {
        int exp = (int)(5*e.getLevel() + oRandom.nextGaussian());
        curExp += exp;
        if (curExp > maxExp)
            curExp = maxExp;
        if (e.getCurHp() == 0) {
            System.out.println("딱뎀!!!\n" + e.IconNameLv() + "을(를) 처리했습니다.\n"
                    + "더 나은 보상(100Gold)과 경험치(" + exp + ")을 획득합니다.\n");
            gold += 100;
        } else {
            System.out.println(e.IconNameLv() + "을(를) 처리했습니다.\n"
                    + " 보상(50Gold)과 경험치(" + exp + ")을 획득합니다.\n");
            gold += 50;
        }
    }

    public void levelUp() {
        if (curExp >= 10 * this.getLevel()) {
            curExp = 0;
            setLevel(getLevel() + 1);
            maxExp = 10 * getLevel();
            setMaxHp((int) (10 + oRandom.nextDouble(1, 5) * MyUtility.pow(2, (getLevel() / 5))));
            setCurHp(getMaxHp());
            System.out.println("레벨업! " + showInfo() + '\n');
        }
    }

    public void drawWeapon() {
        if (gold >= 200) {
            gold -= 200;
            Weapon w = new Weapon(getLevel());
            System.out.println("New Weapon!\n" + w.showInfo() + "\n이 무기를 가지시겠습니까? (Y/N)");
            String ans = "";
            while (!ans.equals("Y") && !ans.equals("N")) {
                ans = scanner.next();
                if (ans.equals("Y")) {
                    weapons.add(w);
                    if(w.getATK()>=weapon.getATK())
                        weapon = w;
                    System.out.println(w.IconNameLv() + "을(를) 추가하였습니다!");
                    
                }
            }
        } else
            System.out.println("돈이 부족합니다. " + IconGold());
    }

    public void goHospital() {
        if (gold >= 50) {

            if (getCurHp() < getMaxHp()) {
                setCurHp(getMaxHp());
                System.out.println("치료가 완료되었습니다!\n" + IconHp() + IconGold());
                gold -= 50;
            } else
                System.out.println("체력이 충분합니다\n" + IconHp() + IconGold());
        } else
            System.out.println("돈이 부족합니다. " + IconGold());
    }

    //getter
    public int getGold() {return gold;}
    public int getCurExp() {return curExp;}
    public int getMaxExp() {return maxExp;}
    public Weapon getWeapon() {return weapon;}
    //setter
    public void setGold(int newGold) {gold = newGold;}
    public void setCurExp(int newExp) {curExp = newExp;}
    public void setMaxExp(int newExp) {maxExp = newExp;}
    public void setWeapon(Weapon newWeapon) {weapon = newWeapon;}
}

//Enemy class
class Enemy extends Unit {
    Enemy(int level) {
        super("", level);
        try {
            String name = MyUtility.randomName("Enemy(name).txt");
            if (name.equals("")) {
                System.out.println("Enemy(name).txt is empty.");
                System.exit(0);
            }
            this.setName(name);
        } catch (Exception ex) {
            System.out.println("no match file : Enemy(name).txt.");
            System.exit(0);
        }
    }
}