package net.sleepyspeller.extralectern;

import net.sleepyspeller.extralectern.commands.CommandReloadConfig;
import net.sleepyspeller.extralectern.events.EventPlayerTakeBook;
import net.sleepyspeller.extralectern.events.EventItemDrop;
import net.sleepyspeller.extralectern.events.EventPlayerInteract;
import net.sleepyspeller.extralectern.events.EventPlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ExtraLectern extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Loading ExtraLectern, hold tight...");

        // Init events, commands, etc...

        // Init events
        getServer().getPluginManager().registerEvents(new EventItemDrop(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerTakeBook(this), this);

        // Init commands
        Objects.requireNonNull(getServer().getPluginCommand("reloadconfig")).setExecutor(new CommandReloadConfig(this));

        Bukkit.getConsoleSender().sendMessage("[ExtraLectern] ExtraLectern loaded and active!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Shutting down...");
    }
}
