package location;
import player.Player;

import static game.Game.menu;
import static loading.Loading.loading;

public abstract class Location {
    protected Player player;
    protected String locationName;

    public Location(Player player, String locationName) {
        this.player = player;
        this.locationName = locationName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public abstract boolean onLocation();
    public void leaveLocation() {

        System.out.printf("Leaving the %s...\n", getLocationName());
        loading(1000);
        menu();
    }
}
