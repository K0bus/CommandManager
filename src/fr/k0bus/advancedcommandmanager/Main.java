package fr.k0bus.advancedcommandmanager;

import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.advancedcommandmanager.commands.*;
import fr.k0bus.advancedcommandmanager.event.*;
import fr.k0bus.advancedcommandmanager.manager.ConfigManager;

public class Main extends JavaPlugin {

    public ConfigManager mainConf;

    @Override
    public void onEnable() {
        this.getLogger().log(Level.INFO, "=============================================================");
        this.getLogger().log(Level.INFO, "       _____                                           ____  ___                                  ");
        this.getLogger().log(Level.INFO, "      /  __ \\                                         | |  \\/  |                                  ");
        this.getLogger().log(Level.INFO, "  __ _| /  \\/ ___  _ __ ___  _ __ ___   __ _ _ __   __| | .  . | __ _ _ __   __ _  __ _  ___ _ __ ");
        this.getLogger().log(Level.INFO, " / _` | |    / _ \\| '_ ` _ \\| '_ ` _ \\ / _` | '_ \\ / _` | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|");
        this.getLogger().log(Level.INFO, "| (_| | \\__/\\ (_) | | | | | | | | | | | (_| | | | | (_| | |  | | (_| | | | | (_| | (_| |  __/ |   ");
        this.getLogger().log(Level.INFO, " \\__,_|\\____/\\___/|_| |_| |_|_| |_| |_|\\__,_|_| |_|\\__,_\\_|  |_/\\__,_|_| |_|\\__,_|\\__, |\\___|_|   ");
        this.getLogger().log(Level.INFO, "=============================================================");
        this.getLogger().log(Level.INFO, "Created by K0bus for AkuraGaming");
        this.getLogger().log(Level.INFO, "=============================================================");
        this.loadConfigManager();
        this.registerEvent(this.getServer().getPluginManager());
        this.registerCommand();
        this.getLogger().log(Level.INFO, "=============================================================");
    }

    public void loadConfigManager() {
        this.getLogger().log(Level.INFO, "Loading configuration ...");
        this.mainConf = new ConfigManager("config.yml", this.getDataFolder(), this);
        this.getLogger().log(Level.INFO, "Configuration loaded successfully !");
    }
	private void registerEvent(PluginManager pm)
	{
        this.getLogger().log(Level.INFO, "Loading event ...");
        pm.registerEvents(new PlayerCommandPreprocess(this), this);
        this.getLogger().log(Level.INFO, "Event loaded successfully !");
    }
    private void registerCommand()
    {
        this.getCommand("acmdmanager").setExecutor(new MainCommand(this));
    }
    public FileConfiguration getConfig()
    {
        return this.mainConf.getConfig();
    }
    @Override
    public void onDisable()
    {
        
    }
}