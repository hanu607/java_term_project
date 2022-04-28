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

        System.out.println("딱뎀의 왕이 되려는 자... 당신의 이름은..?");
        String name = scanner.nextLine();
        while (name.equals("")) {
            System.out.println("이름이 없을 수가 있는가...");
            name = scanner.nextLine();
        }
        System.out.println("Hello, " + name + '\n' + "딱뎀을 잘 맞출 자신이 있는가... (Y or N)");
        String ans = scanner.next();
        if (ans.equals("Y")) {
            hero.setName(name);

            System.out.println("\n모험을 시작합니다...\n");
        } else {
            System.out.println("준비가 되면 다시 오게...");
            System.exit(0);
        }
    }

    public void goHunt() {
        Enemy enemy = new Enemy(hero.getLevel());
        System.out.println(enemy.showInfo() + "을(를) 마주쳤습니다!\n\n" + hero.WeaponList());

        while (hero.isAlive()) {
            System.out.println("사용할 무기를 고르십시오.");
            hero.changeWeapon();
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
        hero.levelUp();
    }

    public void action() {
        System.out.println("무슨 행동을 하시겠습니까?");
        System.out.println("1.사냥 2.치료 3.제작 4.상태");

        int n = scanner.nextInt();

        switch (n) {
        case 1: // 사냥
            System.out.println("사냥터로 이동 중 입니다...");
            goHunt();
            break;
        case 2: // 치료
            System.out.println("병원으로 이동 중 입니다...");
            hero.goHospital();
            break;
        case 3: // 제작
            System.out.println("제작소로 이동 중 입니다...");
            hero.drawWeapon();
            break;
        case 4: // 상태
            System.out.println(hero.showInfo() + '\n');
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
