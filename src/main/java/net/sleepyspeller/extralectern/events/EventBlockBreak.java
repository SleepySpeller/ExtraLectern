package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EventBlockBreak implements Listener {
    private final ExtraLectern plugin;
    public EventBlockBreak(ExtraLectern plugin){this.plugin = plugin;}

    @EventHandler
    public void onBlockBroken(BlockBreakEvent event) {
        if(!event.getPlayer().isOp()) {
            event.setCancelled(true);
        }
    }
}
