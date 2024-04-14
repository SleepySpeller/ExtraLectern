package net.sleepyspeller.extralectern.libs;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SetCompassDelay extends BukkitRunnable {
    private ExtraLectern plugin;
    private Player player;
    private Location location;

    public SetCompassDelay(ExtraLectern plugin, Player player, Location location) {
        this.plugin = plugin;
        this.player = player;
        this.location = location;
    }

    @Override
    public void run() {
        player.setCompassTarget(location);
    }

    public BukkitRunnable getTask(){
        return this;
    }
}