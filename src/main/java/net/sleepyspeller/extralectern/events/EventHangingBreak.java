package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;

public class EventHangingBreak implements Listener {
    private final ExtraLectern plugin;
    public EventHangingBreak(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onHangingBreak(HangingBreakByEntityEvent event) {
        if(event.getRemover() instanceof Player) {
            Player player = (Player) event.getRemover();
            if(!player.isOp()){
                event.setCancelled(true);
            }
        }
    }
}