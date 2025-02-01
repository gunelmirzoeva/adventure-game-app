import game.Game;
import static sounds.SoundManager.musicStopOrPlay;
import static sounds.SoundManager.playSound;


public class AdventureApp {
    public static void main(String[] args) {
        new Thread(() -> playSound("src/sounds/music/start.wav")).start();
        musicStopOrPlay();
        Game.start();
        System.out.println("Hello World");
    }
}
