package location;
import loading.Loading;
import player.Player;
import static loading.Loading.showLoadingBar;
import static game.Game.menu;
import static sounds.SoundManager.playSoundEffect;

public class SafeHouse extends Location {


    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\n-------------SAFE HOUSE---------------\n");
        System.out.println("You have entered the " + getLocationName());
        if(player.getHealth() == player.getCharacterType().getBaseHealth()) {
            System.out.println("Your health is already full!");
            System.out.println("\n--------------------------------------\n\n\n");
            menu();
        }
        else {
            showLoadingBar(20,30);
            new Thread(() -> playSoundEffect("src/sounds/music/health.wav")).start();

            player.restoreHealth();
            System.out.println("\nYour health has been restored to " + player.getHealth() + "!");
            System.out.println("\nLeaving Safe House...\n\n");
            Loading.loading(1000);
            menu();
        }
        return true;
    }

}
