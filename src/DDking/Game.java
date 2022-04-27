package DDking;
import DDking.Unit;
import DDking.Weapon;
import java.io.File;
import java.util.Scanner;
public class Game {
    private Hero hero;
    Scanner scanner = new Scanner(System.in);
    public void start() {
        
        System.out.println("딱뎀의 왕이 되려는 자... 당신의 이름은..?");
        String name = scanner.nextLine();
        while(name.equals("")) {
            System.out.println("이름이 없을 수가 있는가...");
            name = scanner.nextLine();
        }
        System.out.println("Hello, " + name + '\n' + "딱뎀을 잘 맞출 자신이 있는가... (Y or N)");
        String ans = scanner.next();                           
        if(ans.equals("Y")) {
            hero = new Hero(name,1);
            System.out.print("당신의 정보 : ");
            System.out.println(hero.showInfo() + "모험을 시작합니다..." + '\n');
        }
        else {
            System.out.println("준비가 되면 다시 오게...");
            System.exit(0);
        }
    }
        
    public void goHunt() {
        
    }
    
    public void action() {
        System.out.println("무슨 행동을 하시겠습니까?");
        System.out.println("1.사냥 2.치료 3.제작 4.상태");
        
        int n = scanner.nextInt();
       
        switch(n) {
        case 1: // 사냥
            System.out.println("사냥터로 이동 중 입니다...");
            goHunt();
            break;
        case 2: // 치료
            System.out.println("병원으로 이동 중 입니다...");
            hero.goHospital();
            break;
        case 3: //제작
            System.out.println("제작 중 입니다...");
            break;
        case 4: //상태
            System.out.println(hero.showInfo());
            break;
        }
    }
    
    public Hero getHero() {return hero;}
}
