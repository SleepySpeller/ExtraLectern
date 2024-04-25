package net.sleepyspeller.extralectern.commands;

import net.sleepyspeller.extralectern.ExtraLectern;
import net.sleepyspeller.extralectern.libs.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandReloadConfig implements CommandExecutor {
    private final ExtraLectern plugin;
    public CommandReloadConfig(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!sender.isOp()) {
            sender.sendMessage("No permission.");
        }
        plugin.reloadConfig(); // reads the config again from the disk, effectivley refreshing it

        List<Player> activePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());

        ConfigManager config = new ConfigManager(plugin);

        for(Player player: activePlayers) {
            player.setCompassTarget(config.getCurrentLoc(player));
        }

        sender.sendMessage("[ExtraLectern] Successfully reloaded the config!");
        return true;
    }
}
