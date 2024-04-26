package net.sleepyspeller.extralectern.events;

import net.sleepyspeller.extralectern.libs.ConfigManager;
import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Lectern;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerInteract implements Listener {
    private final ExtraLectern plugin;
    public EventPlayerInteract(ExtraLectern plugin) {this.plugin = plugin;}

    private final Material[] restrictedBlocks = new Material[]{
            Material.LEVER,

            Material.IRON_TRAPDOOR,
            Material.OAK_TRAPDOOR,
            Material.SPRUCE_TRAPDOOR,
            Material.BIRCH_TRAPDOOR,
            Material.JUNGLE_TRAPDOOR,
            Material.ACACIA_TRAPDOOR,
            Material.CHERRY_TRAPDOOR,
            Material.DARK_OAK_TRAPDOOR,
            Material.MANGROVE_TRAPDOOR,
            Material.BAMBOO_TRAPDOOR,
            Material.CRIMSON_TRAPDOOR,
            Material.WARPED_TRAPDOOR,

            Material.PAINTING,

            Material.ITEM_FRAME,

            Material.FLOWER_POT,

            Material.HOPPER,

            Material.OAK_FENCE_GATE,
            Material.SPRUCE_FENCE_GATE,
            Material.BIRCH_FENCE_GATE,
            Material.JUNGLE_FENCE_GATE,
            Material.ACACIA_FENCE_GATE,
            Material.CHERRY_FENCE_GATE,
            Material.DARK_OAK_FENCE_GATE,
            Material.MANGROVE_FENCE_GATE,
            Material.BAMBOO_FENCE_GATE,
            Material.CRIMSON_FENCE_GATE,
            Material.WARPED_FENCE_GATE,

            Material.NOTE_BLOCK,

            Material.SHULKER_BOX,

            Material.BLAST_FURNACE,

            Material.ANVIL,
            Material.CHIPPED_ANVIL,
            Material.DAMAGED_ANVIL,

            Material.BARREL,

            Material.DROPPER,
    };

    public void openBook(PlayerInteractEvent event){
        BlockState state = event.getClickedBlock().getState();
        if (state instanceof Lectern) {
            Lectern lectern = (Lectern) state;
            ItemStack book = lectern.getInventory().getItem(0);

            if (book != null && book.getType() == Material.WRITTEN_BOOK) {
                event.getPlayer().openBook(book);
                event.setCancelled(true);
            }
        }
    }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getClickedBlock() instanceof ItemFrame && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            return;
        }

        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            if (event.getClickedBlock().getType() == Material.LECTERN) {
                ConfigManager config = new ConfigManager(plugin);

                if (config.isLecternValid(event.getClickedBlock().getLocation(), event.getPlayer())) {
                    Location newLoc = config.getNextLoc(event.getPlayer());

                   if(newLoc != null) {
                       event.getPlayer().setCompassTarget(newLoc);
                   }

                    config.increaseIndex(event.getPlayer().getName());

                   openBook(event);
                } else if(config.isIndexLastLecternByUsername(event.getPlayer().getName()) && !config.getLastClickStatus(event.getPlayer().getName()) && config.isLastLecternClicked(event.getClickedBlock().getLocation())) {
                    event.getPlayer().sendMessage(config.getFinalMessage());
                    event.getPlayer().getInventory().remove(Material.COMPASS);
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

                    config.setLastClickStatus(event.getPlayer().getName(), true);
                    openBook(event);
                } else {
                    if(config.getIndex(event.getPlayer().getName()) < config.getIndex(event.getClickedBlock().getLocation())){

                        if(!config.getLastClickStatus(event.getPlayer().getName())){
                            event.getPlayer().sendMessage("You need to find the next Lectern first! Use the compass to help!");
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
                        }
                        event.setCancelled(true);
                    }
                }


            } else  if(!event.getPlayer().isOp()){

                // This could probably be done a lot better but this also works
                boolean isInvalidBlockClicked = false;
                for(Material material : restrictedBlocks) {

                    if(event.getClickedBlock().getType() == material) {
                        isInvalidBlockClicked = true;
                        break;
                    }
                }

                if(isInvalidBlockClicked) {
                    event.setCancelled(true);
                }
            }
        }
    }
}

