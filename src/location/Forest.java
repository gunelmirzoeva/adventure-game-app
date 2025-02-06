package location;
import player.Player;

public class Forest extends Location {

    public Forest(Player player) {
        super(player, "Forest");
    }


    @Override
    public boolean onLocation() {
        setLocationName("Forest");
        System.out.println("You have entered the " + getLocationName());
        return false;
    }
}
