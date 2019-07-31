package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AddBlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        List<String> blocks = config.getStringList("blocks");
        List<String> permissions = config.getStringList("permissions");
        String[] block = blocks.toArray(new String[0]);
        String[] permission = permissions.toArray(new String[0]);
        int found = 0;
        int z = 0;
        int x = Array.getLength(block);
        int y = Array.getLength(permission);

        if (commandSender.hasPermission(config.getString("AddBlockPermission"))) {
            if (strings.length == 2) {
                if(!(Material.getMaterial(strings[0]) == null)) {
                    if (x == y) {
                        while(z < x) {
                            if (strings[0].equals(block[z]) || strings[0].equals(permission[z]) || strings[1].equals(block[z]) || strings[1].equals(permission[z])){
                                found++;
                            }
                            z++;
                        }
                            if (found == 0) {
                                commandSender.sendMessage(ChatColor.GREEN + config.getString("message.command-correct") + " [ " + strings[0] + " , " + strings[1] + " ]");
                                blocks.add(strings[0]);
                                config.set("blocks", blocks);
                                permissions.add(strings[1]);
                                config.set("permissions", permissions);
                                Main.getInstance().saveConfig();
                            } else {
                                commandSender.sendMessage("§c" + config.getString("message.warn-AlreadyAddedBlock") + " [ " + strings[0] + " , " + strings[1] +  " ]");
                            }
                    } else {
                        commandSender.sendMessage("§c" + config.getString("message.PermissionsBlocksDismatch") + " [ " + x + " , " + y + " ]");
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-NotMaterial") + " [ " + strings[0] + " ]");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-format"));
            }
        } else{
            commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-perm") + " [" + config.getString("AddBlockPermission") + "]");
        }
        return true;
    }
}
