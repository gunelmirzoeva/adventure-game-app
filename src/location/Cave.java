package location;
import player.Player;

public class Cave extends Location {

    public Cave(Player player) {
        super(player, "Cave");
    }


    @Override
    public boolean onLocation() {
        System.out.println("You have entered the cave");
        return false;
    }
}
