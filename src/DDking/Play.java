package DDking;

import DDking.Game;
import java.util.Random;

public class Play {
    public static void main(String[] args) {
        System.out.println("DDakk DDam King");
        Game game = new Game();
        game.start();
        while (game.getIsRun()) {
            game.action();
        }
        System.out.println("게임을 종료 합니다.");
    }
}
