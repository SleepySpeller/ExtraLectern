package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Painting;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EventPlayerInteractEntity implements Listener {
    private final ExtraLectern plugin;
    public EventPlayerInteractEntity(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerEntityInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame || event.getRightClicked() instanceof Painting) {
            if(!event.getPlayer().isOp()) {
                event.setCancelled(true);
            }
        }
    }
}
