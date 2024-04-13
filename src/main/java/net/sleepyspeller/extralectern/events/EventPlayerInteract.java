package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ConfigManager;
import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventPlayerInteract implements Listener {
    private final ExtraLectern plugin;

    public EventPlayerInteract(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if(event.getClickedBlock().getType() == Material.LECTERN) {
                ConfigManager config = new ConfigManager(plugin);

                if(config.isLecternValid(event.getClickedBlock().getLocation(), event.getPlayer())) {

                    event.getPlayer().setCompassTarget(config.getNextLoc(event.getPlayer())); // ERR
                    config.increaseIndex(event.getPlayer().getName());
                }

//                ConfigPlayerHandler playerHandler = new ConfigPlayerHandler(plugin);
//                ConfigLecternHandler lecternHandler = new ConfigLecternHandler(plugin);
//
//                Player player = event.getPlayer();
//
//                int index = playerHandler.getIndexByUsername(player.getName());
//
//                String newCoords = lecternHandler.getNextCoordsByIndex(index);
//                List<String> XYZ = List.of(newCoords.split(" "));
//
//                Location newLoc = new Location(player.getWorld(), Double.parseDouble(XYZ.get(0)), Double.parseDouble(XYZ.get(1)), Double.parseDouble(XYZ.get(2)));
//
//                playerHandler.setIndexByUsername(player.getName(), index+1);
//
//                player.setCompassTarget(newLoc);
            }
        }
//        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//            event.getPlayer().sendMessage("Clicked right click!");
//
//            //TODO
////            if(event.getClickedBlock().getType() != DOOR || Material.LECTERN){
////                event.setCancelled(true);
////            }
//            // Make an enum for all the doors and have
//            // a loop check if player click on one of those
//            // if he didnt click on a door or a lectern, cancel the event.
//            if(Objects.requireNonNull(event.getClickedBlock()).getType() == Material.LECTERN) {
//                Player player = event.getPlayer();
//
//                ArrayList<String> lecternPlacements = (ArrayList<String>) plugin.getConfig().getList("locations");
//                int lastLecternIndex = plugin.getConfig().getInt("players." + player.getName());
//
//
//                assert lecternPlacements != null;
//                if(lastLecternIndex - 1 <= lecternPlacements.size()) {
//                    return;
//                }
//
//
//
//                String newCoordsString = lecternPlacements.get(lastLecternIndex+1);
//                List<String> XYZ = List.of(newCoordsString.split(" "));
//
//                Location newLecternLocation = new Location(player.getWorld(), Double.parseDouble(XYZ.get(0)), Double.parseDouble(XYZ.get(1)), Double.parseDouble(XYZ.get(2)));
//
//                player.setCompassTarget(newLecternLocation);
//
//                plugin.getConfig().set("players." + player.getName(), lastLecternIndex + 1);
//                plugin.reloadConfig();


//        event.getPlayer().sendMessage("Clicked on a lectern");

//                ArrayList<String> blockPlacements = (ArrayList<String>) plugin.getConfig().getList("locations");
//                event.getPlayer().sendMessage("Found coords at " + blockPlacements.get(0) + " and " + blockPlacements.get(1));

//                Block clickedBlock = event.getClickedBlock();
//                Location clickedBlockLocation = clickedBlock.getLocation();
//
//                String StringClickedLocation = String.valueOf(clickedBlockLocation.getBlockX()) + " " + String.valueOf(clickedBlockLocation.getBlockY()) + " " + String.valueOf(clickedBlockLocation.getBlockZ());
//
//                try {
//                    event.getPlayer().sendMessage("doing a try...");
//                    ArrayList<String> ListConfig = (ArrayList<String>) plugin.getConfig().getList("locations");
//
//                    boolean newItemFound = false;
//
//                    assert ListConfig != null;
//                    for(String currentLoopCoords : ListConfig) {
//                        event.getPlayer().sendMessage("looping for " + currentLoopCoords);
//                        event.getPlayer().sendMessage("with StringClickedLocation " + StringClickedLocation);
//
//                        if(currentLoopCoords.equals(StringClickedLocation)){
//                            event.getPlayer().sendMessage("found the block clicked");
//
//                            newItemFound = true;
//                            continue;
//                        }
//
//                        if(newItemFound) {
//                            event.getPlayer().sendMessage("setting a new direction!");
//                            List<String> XYZcurrentLoopCoords = List.of(currentLoopCoords.split(" "));
//
//                            Location newLoc = new Location(event.getClickedBlock().getWorld(), Double.parseDouble(XYZcurrentLoopCoords.get(0)), Double.parseDouble(XYZcurrentLoopCoords.get(1)), Double.parseDouble(XYZcurrentLoopCoords.get(2)));
//
//                            event.getPlayer().setCompassTarget(newLoc);
//                            break;
//                        }
//                    }
//
//                } catch (Exception e) {
//                    event.getPlayer().sendMessage("An error occured with ExtraLectern!");
//                    return;
//                }
//
//                event.getPlayer().sendMessage("clicked a lectern!");
//                event.getPlayer().setCompassTarget(event.getClickedBlock().getLocation());
//            }
//        }
    }
}

