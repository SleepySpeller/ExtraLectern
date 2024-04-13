package net.sleepyspeller.extralectern.commands;

import net.sleepyspeller.extralectern.ExtraLectern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandReloadConfig implements CommandExecutor {
    private final ExtraLectern plugin;
    public CommandReloadConfig(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        plugin.reloadConfig(); // reads the config again from the disk, effectivley refreshing it

        sender.sendMessage("[ExtraLectern] Successfully reloaded the config!");
        return true;
    }
}
