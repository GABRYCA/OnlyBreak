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
import java.util.Set;

public class AddBlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();

        if (commandSender.hasPermission(config.getString("AddBlockPermission"))) {
            if (strings.length == 2) {
                if(!(Material.getMaterial(strings[0]) == null)) {
                    config.set("blocks." + strings[0] + ".block", strings[0]);
                    config.set("blocks." + strings[0] + ".permission", strings[1]);
                    Main.getInstance().saveConfig();
                    commandSender.sendMessage("§a" + config.get("message.command-correct"));
                } else {
                    commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-NotMaterial") + " [ " + strings[0] + " ]");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-format"));
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + config.getString("message.warn-perm") + " [" + config.getString("AddBlockPermission") + "]");
        }
        return true;
    }
}
