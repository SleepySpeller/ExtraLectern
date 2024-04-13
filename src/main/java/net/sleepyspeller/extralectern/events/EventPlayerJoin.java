package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ConfigManager;
import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventPlayerJoin implements Listener {
    private final ExtraLectern plugin;
    public EventPlayerJoin(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        Bukkit.getConsoleSender().sendMessage("Player joined!");
        ConfigManager config = new ConfigManager(plugin);
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {

            config.setIndex(player.getName(), 0);

            Bukkit.getConsoleSender().sendMessage("Player never played, setting a new compass for them");
            event.getPlayer().setCompassTarget(config.getCurrentLoc(player));
            player.updateInventory();
        } else {
            Bukkit.getConsoleSender().sendMessage("Player played before, setting the old settings");
            player.setCompassTarget(config.getCurrentLoc(player));
            player.updateInventory();
        }
//        if(!event.getPlayer().hasPlayedBefore()) {
//            ConfigPlayerHandler playerHandler = new ConfigPlayerHandler(plugin);
//            playerHandler.setIndexByUsername(event.getPlayer().getName(), 0);
//        } else {
//            Player player = event.getPlayer();
//
//            ConfigPlayerHandler playerHandler = new ConfigPlayerHandler(plugin);
//            ConfigLecternHandler lecternHandler = new ConfigLecternHandler(plugin);
//
//            int index = playerHandler.getIndexByUsername(player.getName());
//            String newStringLocation = lecternHandler.getXYZByIndex(index);
//
//            List<String> XYZ = List.of(newStringLocation.split(" "));
//
//            Location newLoc = new Location(player.getWorld(), Double.parseDouble(XYZ.get(0)), Double.parseDouble(XYZ.get(1)), Double.parseDouble(XYZ.get(2)));
//            player.setCompassTarget(newLoc);
//        }
    }
}
