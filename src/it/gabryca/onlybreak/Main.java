package it.gabryca.onlybreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;

    public static Main getInstance() {
        return plugin;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Onbreak(), this);
        System.out.println(ChatColor.GREEN + "[OnlyBreak] Plugin abilitato con successo.");
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[OnlyBreak] Plugin disabilitato con successo.");
    }

}
