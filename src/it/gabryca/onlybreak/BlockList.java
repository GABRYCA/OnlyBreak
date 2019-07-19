package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BlockList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender.hasPermission(Main.getInstance().getConfig().getString("BlockListPermission"))){
            commandSender.sendMessage(ChatColor.DARK_GRAY + Main.getInstance().getConfig().getString("Block") + " [ " + Main.getInstance().getConfig().getStringList("blocks") + " ] " + ChatColor.GRAY + Main.getInstance().getConfig().getString("Permission") + " [ " + Main.getInstance().getConfig().getStringList("permissions") + " ]");
        }else{
            commandSender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockListPermission") + " ]");
        }

        return true;
    }
}
