package net.sleepyspeller.extralectern.commands;

import net.sleepyspeller.extralectern.ConfigManager;
import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGetIndex implements CommandExecutor {
    private final ExtraLectern plugin;
    public CommandGetIndex(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        ConfigManager config = new ConfigManager(plugin);

        player.setCompassTarget(config.getCurrentLoc(player));

        return true;
    }
}
