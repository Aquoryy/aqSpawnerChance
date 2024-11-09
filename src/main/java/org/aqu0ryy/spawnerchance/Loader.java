package org.aqu0ryy.spawnerchance;

import org.aqu0ryy.spawnerchance.listeners.SpawnerListener;
import org.aqu0ryy.spawnerchance.utils.ChatUtil;
import org.aqu0ryy.spawnerchance.utils.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class Loader extends JavaPlugin {

    private static Loader inst;

    @Override
    public void onEnable() {
        inst = this;

        if (VaultUtil.setupPermissions()) {
            saveDefaultConfig();

            loadListeners();
            loadCommands();
            loadCompleter();
        } else {
            ChatUtil.sendMessage(Bukkit.getConsoleSender(), "&c[" + getDescription().getName() + "] Установите плагин - Vault");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }

    public static Loader getInst() {
        return inst;
    }

    public void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new SpawnerListener(), this);
    }

    public void loadCommands() {
        getCommand("aqspawnerchance").setExecutor(new Commands());
    }

    public void loadCompleter() {
        getCommand("aqspawnerchance").setTabCompleter(new Commands());
    }
}
