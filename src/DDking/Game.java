package DDking;

import DDking.Unit;
import DDking.Weapon;
import java.io.File;
import java.util.Scanner;

public class Game {
    private Hero hero;
    private boolean isRun;
    Scanner scanner = new Scanner(System.in);

    public Game() {
        hero = new Hero();
        isRun = true;
    }

    public void start() {

        System.out.println("딱뎀의 왕이 되려는 자, 당신의 이름은..?");
        String name = scanner.nextLine();
        while (name.equals("")) {
            System.out.println("이름이 없을 수가 있는가...");
            name = scanner.nextLine();
        }
        System.out.println("반갑네, " + name + '\n' + "딱뎀을 잘 맞출 자신이 있는가... (Y or N)");
        String ans = scanner.next();
        if (ans.equals("Y")) {
            hero.setName(name);

            System.out.println("모험을 시작합니다... 목표 10Lv");
        } else {
            System.out.println("아쉽구만...준비가 되면 다시 오게...꼭...");
            isRun = false;
        }
    }

    public void goHunt() {
        Enemy enemy = new Enemy(hero.getLevel());
        System.out.println(hero.weaponList() + "\n\n" + enemy.showInfo() + "을(를) 마주쳤습니다!");
        while (hero.isAlive()) {
            System.out.println("사용할 무기를 고르십시오.");
            if (0 == hero.changeWeapon()) {
                System.out.println("부끄러운 줄도 모르고 도망쳤습니다...");
                return;
                }
            hero.attack(enemy);
            if (enemy.getCurHp() <= 0)
                break;
            System.out.println(enemy.showInfo());
            enemy.attack(hero);
            System.out.println(hero.IconNameLv() + hero.IconHp());
        }
        if (hero.getCurHp() <= 0) {
            over();
            System.out.println("Game Over");
            return;
        } else {
            hero.kill(enemy);
        }
        if (hero.getLevel() == 10) {
            isRun = false;
            System.out.println("레벨 10!!!" + hero.IconNameLv() + " 자네가 새 [딱뎀킹 : DDKING]이구먼... 이제 나도 편히...쉴 수 있겠어...\n" + hero.showInfo()+"\n\n" + hero.weaponList());
        }
    }

    public void action() {
        System.out.println("\n다음 행동들을 할 수 있다네...");
        System.out.println("1.사냥 2.치료(200G) 3.무기제작(100G) 4.무기삭제(200G) 5.상태");

        String n = scanner.next();

        switch (n) {
        case "1": // 사냥
            System.out.println("사냥터로 이동 중입니다...\n");
            goHunt();
            break;
        case "2": // 치료
            System.out.println("치료 중입니다...\n");
            hero.heal();
            break;
        case "3": // 제작
            System.out.println("제작소로 이동 중입니다...\n");
            hero.drawWeapon();
            break;
        case "4":
            hero.eraseWeapon();
            break;
        case "5": // 상태
            System.out.println(hero.showInfo());
            break;
        default: 
            System.out.println("똑바로 좀 입력하게나... 왜 그러는 건가...대체");
            break;
        }
    }

    public void over() {
        isRun = false;
    }
    
    //getter
    public boolean getIsRun() {return isRun;}
    public Hero getHero() {return hero;}
    //setter
    public void setHero(Hero newHero) {this.hero = newHero;}
}
