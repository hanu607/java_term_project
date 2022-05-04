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
        this.maxHp = oRandom.nextInt(MyUtility.pow(level,4),MyUtility.pow(level,5) + 1);
        curHp = maxHp;
        avgAtk = (oRandom.nextInt(MyUtility.pow(level,4),MyUtility.pow(level,5) + 1))/5;
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
        maxExp = 100;
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
                + "0. 도망가기\n";
        
        int i = 1;
        for (Weapon w : weapons) {
            list += i++ + ". " + w.showInfo() + '\n';
        }
        list += "----------------------------";
        return list;
    }

    public int changeWeapon() {
        int n = 0;
        while (true) {
            try {
                n = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("왜 굳이 그런짓을... 다시 입력하게나.");
                scanner = new Scanner(System.in);
                continue;
            }
            if (0 > n || n > weapons.size()) {
                System.out.println("자네는 무기 개수도 모르는가...? 다시 입력하게나.");
                continue;
            }
            break;
        }
        if (n != 0)
            this.setAvgAtk(weapons.get(n - 1).getATK());
        return n;
    }

    public void kill(Enemy e) {
        int exp = 100 * getLevel();
        if (e.getCurHp() == 0) {
            System.out.println(
                    "딱뎀!!!\n" + e.IconNameLv() + "을(를) 처리했습니다.\n" + "100Gold와 경험치(" + exp + ")을 획득합니다.\n");
            curExp += exp;
            gold += 100;
        } else {
            System.out.println(e.IconNameLv() + "을(를) 무자비하게 처리했습니다...딱뎀킹이 되고 싶지 않은가..?\n");
        }
        if (curExp >= maxExp)
            levelUp();
    }

    public void levelUp() {
        curExp = 0;
        setLevel(getLevel() + 1);
        maxExp = 500 * getLevel();
        setMaxHp(5 * (oRandom.nextInt(MyUtility.pow(getLevel(), 4), MyUtility.pow(getLevel(), 5) + 1)));
        setCurHp(getMaxHp());
        System.out.println("레벨업! " + showInfo() + '\n');
    }

    public void drawWeapon() {
        if (gold >= 100) {
            gold -= 100;
            Weapon w = new Weapon(getLevel());
            System.out.println("New Weapon!\n" + w.showInfo() + "\n이 무기를 가지시겠습니까? (Y/N)");
            String ans = "";
            while (!ans.equals("Y") && !ans.equals("N")) {
                ans = scanner.next();
                if (ans.equals("Y")) {
                    int i;
                    for(i = weapons.size() - 1;i >= 0; i--) {
                        if(weapons.get(i).getATK() <= w.getATK())
                            break;
                    }
                    weapons.add(i+1,w);
                
                    if(w.getATK()>=weapon.getATK())
                        weapon = w;
                    System.out.println(w.IconNameLv() + "을(를) 추가하였습니다!"); 
                } else
                    System.out.println("상점주인 : 마음씨 좋은 총각일세... ");
            }
        } else
            System.out.println("상점주인 : 딱뎀을 더 하고 오시게... " + IconGold());
    }

    public void heal() {
        if (gold >= 200) {

            if (getCurHp() < getMaxHp()) {
                setCurHp(getMaxHp());
                System.out.println("병원장 : 치료가 완료되었습니다. 이제 아프지 마세요!\n" + IconHp() + IconGold());
                gold -= 200;
            } else
                System.out.println("병원장 : 제가 봐드릴게 없네요.\n" + IconHp() + IconGold());
        } else
            System.out.println("병원장 : 환자분은 딱뎀을 더 하고 오셔야겠습니다." + IconGold());
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