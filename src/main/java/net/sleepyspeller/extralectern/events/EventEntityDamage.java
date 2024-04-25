package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EventEntityDamage implements Listener {
    private final ExtraLectern plugin;
    public EventEntityDamage(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof ItemFrame && !event.getDamager().isOp()){
            event.setCancelled(true);
        }
    }
}
