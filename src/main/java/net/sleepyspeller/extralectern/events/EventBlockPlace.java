package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventBlockPlace implements Listener {
    private final ExtraLectern plugin;
    public EventBlockPlace(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }
}
