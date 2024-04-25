package net.sleepyspeller.extralectern.commands;

import net.sleepyspeller.extralectern.ExtraLectern;
import net.sleepyspeller.extralectern.libs.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandResetProgress implements CommandExecutor {
    private final ExtraLectern plugin;
    public CommandResetProgress(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage("No permission");
            return true;
        }

        if(args.length < 1) {
            commandSender.sendMessage("[ExtraLectern] No player specified.");
            return true;
        }

        ConfigManager config = new ConfigManager(plugin);
        config.setIndex(args[0], 0);

        config.setLastClickStatus(args[0], false);

        Player player = Bukkit.getPlayer(args[0]);

        if(player == null) {
            commandSender.sendMessage("[ExtraLectern] Player not connected but the progress has been reset!");
            return true;
        }

        try{
            player.setCompassTarget(config.getLocByIndex(0));
        } catch (Exception e) {
            commandSender.sendMessage("[ExtraLectern] Failed to set the players compass (player is not connected or disconnected during execution)\nSomething's wrong if the player is connected to the server.");
            return true;
        }

        commandSender.sendMessage("[ExtraLectern] Progress for " + args[0] + " reset successfully!");

        return true;
    }
}
