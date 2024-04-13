package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;

public class EventPlayerTakeBook implements Listener {
    private final ExtraLectern plugin;
    public EventPlayerTakeBook(ExtraLectern plugin) {this.plugin = plugin;}

    @EventHandler
    public void onPlayerTakeBook(PlayerTakeLecternBookEvent event) {
        if(!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }
}
