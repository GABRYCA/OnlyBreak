package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddBlock implements CommandExecutor {

    private FileConfiguration config;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> blocks = Main.getInstance().getConfig().getStringList("blocks");
        List<String> permissions = Main.getInstance().getConfig().getStringList("permissions");
        String[] block = blocks.toArray(new String[0]);
        String[] permission = permissions.toArray(new String[0]);
        int x = Array.getLength(block);
        int y = Array.getLength(permission);

        if (commandSender.hasPermission(Main.getInstance().getConfig().getString("AddBlockPermission"))) {
            if (strings.length == 2) {
                if(!(Material.getMaterial(strings[0]) == null)) {
                    if (x == y) {
                        commandSender.sendMessage(ChatColor.GREEN + Main.getInstance().getConfig().getString("message.command-correct") + " [ " + strings[0] + " , " + strings[1] + " ]");
                        blocks.add(strings[0]);
                        Main.getInstance().getConfig().set("blocks", blocks);
                        permissions.add(strings[1]);
                        Main.getInstance().getConfig().set("permissions", permissions);
                        Main.getInstance().saveConfig();
                    } else {
                        commandSender.sendMessage("§c" + Main.getInstance().getConfig().getString("message.PermissionsBlocksDismatch") + " [ " + x + " , " + y + " ]");
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-NotMaterial") + " [ " +strings[0] + " ]");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-format"));
            }
        } else{
            commandSender.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm") + " [" + Main.getInstance().getConfig().getString("AddBlockPermission") + "]");
        }
        return true;
    }
}
