package net.sleepyspeller.extralectern;

import net.sleepyspeller.extralectern.commands.CommandReloadConfig;
import net.sleepyspeller.extralectern.commands.CommandResetProgress;
import net.sleepyspeller.extralectern.commands.CommandSetFinalMessage;
import net.sleepyspeller.extralectern.events.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtraLectern extends JavaPlugin {
    public FileConfiguration indexConfig;
    public File indexFile;

    public FileConfiguration getIndexConfig(){
        return indexConfig;
    }

    public void saveIndexConfig(){
        try{
            indexConfig.save(indexFile);
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Cannot save config!");
        }
    }

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Loading ExtraLectern, hold tight...");

        // Init events, commands, etc...


        // Init events
        getServer().getPluginManager().registerEvents(new EventItemDrop(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerInteract(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerTakeBook(this), this);
        getServer().getPluginManager().registerEvents(new EventBlockBreak(this), this);
        getServer().getPluginManager().registerEvents(new EventBlockPlace(this), this);
        getServer().getPluginManager().registerEvents(new EventHangingBreak(this), this);
        getServer().getPluginManager().registerEvents(new EventPlayerInteractEntity(this), this);
        getServer().getPluginManager().registerEvents(new EventEntityDamage(this), this);

        // Init commands
        Objects.requireNonNull(getServer().getPluginCommand("reloadconfig")).setExecutor(new CommandReloadConfig(this));
        Objects.requireNonNull(getServer().getPluginCommand("resetprogress")).setExecutor(new CommandResetProgress(this));
        Objects.requireNonNull(getServer().getPluginCommand("setfinalmessage")).setExecutor(new CommandSetFinalMessage(this));

        // Init Config
        saveDefaultConfig();

        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        indexFile = new File(getDataFolder(), "playerIndex.yml");

        if(!indexFile.exists()) {
            try{
                indexFile.createNewFile();
                Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Made a new playerIndex.yml.");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Could not create or find playerIndex.yml!");
            }
        }

        indexConfig = YamlConfiguration.loadConfiguration(indexFile);

        Bukkit.getConsoleSender().sendMessage("[ExtraLectern] ExtraLectern loaded and active!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[ExtraLectern] Shutting down...");
    }
}
