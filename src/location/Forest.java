package location;
import player.Player;

public class Forest extends Location {

    public Forest(Player player) {
        super(player, "Forest");
    }

    @Override
    public void enter() {
        System.out.println("You have entered the Forest.");
        onLocation();
    }

    @Override
    public boolean onLocation() {
        return false;
    }
}
