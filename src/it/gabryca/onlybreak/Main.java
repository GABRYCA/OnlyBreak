package it.gabryca.onlybreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        getCommand("DelBlock").setExecutor(new DelBlock());
        getCommand("MyBlocks").setExecutor(new MyBlocks());
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

}
