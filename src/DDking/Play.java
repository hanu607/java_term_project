package DDking;
import DDking.Game;

public class Play {
    public static void main(String[] args) {
        System.out.println("DDakk DDam King");
        Game game = new Game();
        game.start();
        while(game.getIsRun()) {
        game.action();  //action 구현 완료하기
        }
    }
}
