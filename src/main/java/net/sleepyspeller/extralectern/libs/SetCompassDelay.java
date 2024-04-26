package net.sleepyspeller.extralectern.libs;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Location;
import org.bukkit.Sound;
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

        // Has the same issue as the compass, the client and the server wont register it unless i delay the sound effect.
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.0f, 1.0f);
    }

    public BukkitRunnable getTask(){
        return this;
    }
}
