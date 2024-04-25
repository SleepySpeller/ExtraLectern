package net.sleepyspeller.extralectern.commands;

import net.sleepyspeller.extralectern.ExtraLectern;
import net.sleepyspeller.extralectern.libs.ConfigManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandSetFinalMessage implements CommandExecutor {
    private final ExtraLectern plugin;
    public CommandSetFinalMessage(ExtraLectern plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(!commandSender.isOp()) {
            commandSender.sendMessage("No permission.");
            return true;

        }

        if(args.length == 0) {
            commandSender.sendMessage("[ExtraLectern] No message found.");

            return true;
        }

        StringBuilder finalMessage = new StringBuilder();
        for(String item : args) {
            finalMessage.append(" ").append(item);
        }

        ConfigManager config = new ConfigManager(plugin);
        config.setFinalMessage(finalMessage.toString());

        commandSender.sendMessage("[ExtraLectern] Successfully set the final message!");

        return true;
    }
}
