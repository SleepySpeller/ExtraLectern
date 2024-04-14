package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.libs.ConfigManager;
import net.sleepyspeller.extralectern.ExtraLectern;
import net.sleepyspeller.extralectern.libs.SetCompassDelay;
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
        ConfigManager config = new ConfigManager(plugin);
        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {
            config.setIndex(player.getName(), 0);
        }

        SetCompassDelay compassDelay = new SetCompassDelay(plugin, player, config.getCurrentLoc(player));
        compassDelay.runTaskLater(plugin, 2L);
    }
}
