package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EventItemDrop implements Listener {
    private final ExtraLectern plugin;
    public EventItemDrop(ExtraLectern plugin){this.plugin = plugin;}

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if(event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            event.setCancelled(true);
        }
    }
}
