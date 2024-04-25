package net.sleepyspeller.extralectern.libs;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ConfigManager {
    private final ExtraLectern plugin;
    private FileConfiguration indexConfig;
    public ConfigManager(ExtraLectern plugin) {
        this.plugin = plugin;
        indexConfig = plugin.getIndexConfig();
    }

    public Location getCurrentLoc(Player player) {
        ArrayList<String> locations = getLocations();
        int index = getIndex(player.getName());

        if (index == locations.size()) {
            index -= 1;
        }

        try{
            String[] XYZ = locations.get(index).split(" ");
            return new Location(player.getWorld(), Double.parseDouble(XYZ[0]), Double.parseDouble(XYZ[1]), Double.parseDouble(XYZ[2]));
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Location getNextLoc(Player player) {
        if(isIndexLastLecternByUsername(player.getName())) {
            return null;
        }

        int index = getIndex(player.getName()) + 1;

        ArrayList<String> locations = getLocations();

        if (index >= locations.size()) {
            return null;
        }

        String[] XYZ = locations.get(index).split(" ");

        try{
            return new Location(player.getWorld(), Double.parseDouble(XYZ[0]), Double.parseDouble(XYZ[1]), Double.parseDouble(XYZ[2]));
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    public Location getLocByIndex(int index) {
        ArrayList<String> locations = getLocations();

        if(locations.size() < index) {
            return null;
        }

        String[] XYZ = locations.get(index).split(" ");

        return new Location(Bukkit.getServer().getWorld("world"), Double.parseDouble(XYZ[0]), Double.parseDouble(XYZ[1]), Double.parseDouble(XYZ[2]));
    }

    public int getIndex(String username) {
        return indexConfig.getInt("playerIndex." + username);
    }

    public ArrayList<String> getLocations() {
        ArrayList<String> locations = (ArrayList<String>) plugin.getConfig().getList("locations");
        return locations;
    }

    // Used to check if the player clicked on the last lectern
    // Basically avoiding OutOfBounds error
    public boolean isIndexLastLecternByUsername(String username) {
        ArrayList<String> locations = getLocations();

        int listLenght = locations.size();
        int currentIndex = getIndex(username);

        return currentIndex >= listLenght;
    }

    public boolean isLecternValid(Location eventBlockLocation, Player player){
        Location currentLocation = getCurrentLoc(player);

        if(!isIndexLastLecternByUsername(player.getName())) {
            return currentLocation.equals(eventBlockLocation);
        } else {
            return false;
        }
    }

    public void setIndex(String username, int index) {
        indexConfig.set("playerIndex." + username, index);
        plugin.saveIndexConfig();
    }

    public void increaseIndex(String username) {
        if(isIndexLastLecternByUsername(username)) {
            return;
        }

        indexConfig.set("playerIndex." + username, getIndex(username) + 1);
        plugin.saveIndexConfig();
    }

    public void setFinalMessage(String text) {
        plugin.getConfig().set("finalMessage", text);
        plugin.saveConfig();
    }

    public String getFinalMessage() {
        return (String) plugin.getConfig().get("finalMessage");
    }

    public boolean getLastClickStatus(String username) {
        return indexConfig.getBoolean("clickedLast."+username);
    }

    public void setLastClickStatus(String username, boolean status) {
        indexConfig.set("clickedLast." + username, status);
        plugin.saveIndexConfig();
    }
}
