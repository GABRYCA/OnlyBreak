package it.gabryca.onlybreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main config;

    public static Main getInstance(){
        return config;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnBreak(),this);
        getCommand("AddBlock").setExecutor(new AddBlock());
        getCommand("BlockList").setExecutor(new BlockList());
        getCommand("BlockGUI").setExecutor(new BlockGUI());
        System.out.println(ChatColor.GREEN + "[OnlyBreak] Plugin abilitato con successo.");
        this.saveDefaultConfig();
        config = this;
        this.saveConfig();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[OnlyBreak] Plugin disabilitato con successo.");
    }

}
