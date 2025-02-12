import game.Game;

import static sounds.SoundManager.*;
import static util.InputUtil.getText;

public class AdventureApp {
    public static void main(String[] args) {
        new Thread(() -> playMusic("src/sounds/music/start.wav")).start();
        String answer = getText("Do you want to stop the music?(yes/no)").toLowerCase();
        if(answer.equals("yes")) {
            stopMusic();
        }
        Game.start();
    }
}
