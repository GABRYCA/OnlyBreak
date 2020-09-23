package it.gabryca.onlybreak;

import it.gabryca.onlybreak.commands.OBreakCommands;
import it.gabryca.onlybreak.configs.MessagesYML;
import it.gabryca.onlybreak.gui.OnBreakAndListeners;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class OnlyBreak extends JavaPlugin {

    public static OnlyBreak config;

    public static OnlyBreak getInstance(){
        return config;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new OnBreakAndListeners(),this);
        getCommand("OBreak").setExecutor(new OBreakCommands());
        System.out.println(ChatColor.GREEN + "[OnlyBreak] Plugin enabled with success.");
        this.saveDefaultConfig();
        config = this;
        this.saveConfig();
        new MessagesYML();
    }

    @Override
    public void onDisable() {
        System.out.println(ChatColor.RED + "[OnlyBreak] Plugin disabled with success.");
    }

    public static FileConfiguration getMessages(){
        MessagesYML messages = new MessagesYML();
        return messages.getFile();
    }

    public static String format(String format){
        return ChatColor.translateAlternateColorCodes('&', format);
    }

}
