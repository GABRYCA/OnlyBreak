package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Array;
import java.util.List;

public class BlockList implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> blocks = Main.getInstance().getConfig().getStringList("blocks");
        List<String> permissions = Main.getInstance().getConfig().getStringList("permissions");
        String[] block = blocks.toArray(new String[0]);
        String[] permission = permissions.toArray(new String[0]);
        int x = Array.getLength(block);
        int y = Array.getLength(permission);

        if (commandSender.hasPermission(Main.getInstance().getConfig().getString("BlockListPermission"))){
            if (x == y) {
                commandSender.sendMessage(ChatColor.DARK_GRAY + Main.getInstance().getConfig().getString("Block") + " [ " + Main.getInstance().getConfig().getStringList("blocks") + " ] " + ChatColor.GRAY + Main.getInstance().getConfig().getString("Permission") + " [ " + Main.getInstance().getConfig().getStringList("permissions") + " ]");
            }else{
                commandSender.sendMessage("§c" + Main.getInstance().getConfig().getString("message.PermissionsBlocksDismatch") + " [ " + x + " , " + y + " ]");
            }
            }else{
            commandSender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockListPermission") + " ]");
        }

        return true;
    }
}
