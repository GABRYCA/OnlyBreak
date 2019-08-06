package it.gabryca.onlybreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlockList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();

        if (commandSender.hasPermission(config.getString("BlockListPermission"))){
            Set<String> blocks = config.getConfigurationSection("blocks").getKeys(false);
            for (String key : blocks) {
                commandSender.sendMessage( "§7" + config.getString("Block") + ": " + config.getString("blocks." + key + ".block"));
                commandSender.sendMessage("§7— " + "§c" + config.getString("Permission") + ": " + config.getString("blocks." + key + ".permission"));
            }
            }else{
            commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockListPermission") + " ]");
        }
        return true;
    }
}
