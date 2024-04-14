package net.sleepyspeller.extralectern.libs;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ConfigManager {
    private final ExtraLectern plugin;
    public ConfigManager(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    public Location getCurrentLoc(Player player) {
        ArrayList<String> locations = getLocations();
        int index = getIndex(player.getName());

        String[] XYZ = locations.get(index).split(" ");

        try{
            return new Location(player.getWorld(), Double.parseDouble(XYZ[0]), Double.parseDouble(XYZ[1]), Double.parseDouble(XYZ[2]));
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Location getNextLoc(Player player) {
        if(isLastLecternByUsername(player.getName())) {
            return null;
        }

        int index = getIndex(player.getName()) + 1;
        player.sendMessage(String.valueOf(index));
        ArrayList<String> locations = getLocations();

        String[] XYZ = locations.get(index).split(" ");

        try{
            return new Location(player.getWorld(), Double.parseDouble(XYZ[0]), Double.parseDouble(XYZ[1]), Double.parseDouble(XYZ[2]));
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public int getIndex(String username) {
        return plugin.getConfig().getInt("playerIndex." + username);
    }

    public ArrayList<String> getLocations() {
        ArrayList<String> locations = (ArrayList<String>) plugin.getConfig().getList("locations");
        return locations;
    }

    // Used to check if the player clicked on the last lectern
    // Basically avoiding OutOfBounds error
    public boolean isLastLecternByUsername(String username) {
        ArrayList<String> locations = getLocations();

        int listLenght = locations.size()-1;
        int currentIndex = getIndex(username);

        return currentIndex >= listLenght;
    }

    public boolean isLecternValid(Location eventBlockLocation, Player player){
        Location currentLocation = getCurrentLoc(player);

        if(!isLastLecternByUsername(player.getName())) {
            return currentLocation.equals(eventBlockLocation);
        } else {
            return false;
        }
    }

    public void setIndex(String username, int index) {
        plugin.getConfig().set("playerIndex." + username, index);
        plugin.saveConfig();
    }

    public void increaseIndex(String username) {
        plugin.getConfig().set("playerIndex." + username, getIndex(username) + 1);
        plugin.saveConfig();
    }


}
