package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import java.lang.reflect.Array;
import java.util.List;

public class BlockList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();

        List<String> blocks = config.getStringList("blocks");
        List<String> permissions = config.getStringList("permissions");
        String[] block = blocks.toArray(new String[0]);
        String[] permission = permissions.toArray(new String[0]);
        int x = Array.getLength(block);
        int y = Array.getLength(permission);

        if (commandSender.hasPermission(config.getString("BlockListPermission"))){
            if (x == y) {
                commandSender.sendMessage(ChatColor.DARK_GRAY + config.getString("Block") + " [ " + config.getStringList("blocks") + " ] " + ChatColor.GRAY + config.getString("Permission") + " [ " + config.getStringList("permissions") + " ]");
            }else{
                commandSender.sendMessage("§c" + config.getString("message.PermissionsBlocksDismatch") + " [ " + x + " , " + y + " ]");
            }
            }else{
            commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockListPermission") + " ]");
        }

        return true;
    }
}
