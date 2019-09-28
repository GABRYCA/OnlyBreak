package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import java.util.Set;

public class BlockList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (!(commandSender.hasPermission(config.getString("BlockListPermission")))){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockListPermission") + " ]");
            return true;
        }

            Set<String> blocks = config.getConfigurationSection("blocks").getKeys(false);
            for (String key : blocks) {
                commandSender.sendMessage( "§7" + config.getString("Block") + ": " + config.getString("blocks." + key + ".block"));
                commandSender.sendMessage("§7— " + "§c" + config.getString("Permission") + ": " + config.getString("blocks." + key + ".permission"));
            }
        return true;
    }
}
