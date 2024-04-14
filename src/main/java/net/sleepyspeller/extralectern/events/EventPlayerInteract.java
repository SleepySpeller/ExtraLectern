package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.libs.ConfigManager;
import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventPlayerInteract implements Listener {
    private final ExtraLectern plugin;

    private final Material[] restrictedBlocks = new Material[]{
            Material.OAK_DOOR,
            Material.SPRUCE_DOOR,
            Material.BIRCH_DOOR,
            Material.JUNGLE_DOOR,
            Material.ACACIA_DOOR,
            Material.CHERRY_DOOR,
            Material.DARK_OAK_DOOR,
            Material.MANGROVE_DOOR,
            Material.BAMBOO_DOOR,
            Material.CRIMSON_DOOR,
            Material.WARPED_DOOR,
            Material.COPPER_DOOR,
            Material.EXPOSED_COPPER_DOOR,
            Material.WEATHERED_COPPER_DOOR,
            Material.OXIDIZED_COPPER_DOOR,
            Material.WAXED_COPPER_DOOR,
            Material.WAXED_WEATHERED_COPPER_DOOR,
            Material.WAXED_OXIDIZED_COPPER_DOOR,
    };


    public EventPlayerInteract(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if (event.getClickedBlock().getType() == Material.LECTERN) {
                ConfigManager config = new ConfigManager(plugin);

                if (config.isLecternValid(event.getClickedBlock().getLocation(), event.getPlayer())) {
                    event.getPlayer().setCompassTarget(config.getNextLoc(event.getPlayer()));

                    config.increaseIndex(event.getPlayer().getName());
                }

            } else {
                if(!event.getPlayer().isOp()){

                    for(Material material : restrictedBlocks) {

                        if(event.getClickedBlock().getType() == material) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
}

