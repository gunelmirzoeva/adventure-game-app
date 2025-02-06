package location;
import player.Player;

public class River extends Location {

    public River(Player player) {
        super(player, "River");
    }

    @Override
    public boolean onLocation() {
        System.out.println("You have entered the " + getLocationName());
        return false;
    }
}
