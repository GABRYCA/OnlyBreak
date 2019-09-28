package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class AddBlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (!(commandSender.hasPermission(config.getString("AddBlockPermission")))){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-perm") + " [" + config.getString("AddBlockPermission") + "]");
            return true;
        }

        if (!(strings.length == 2)){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-format"));
            return true;
        }

        if (Material.getMaterial(strings[0]) == null){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-NotMaterial") + " [ " + strings[0] + " ]");
            return true;
        }

                    if(config.getString("blocks." + strings[0] + ".block") == null) {
                        config.set("blocks." + strings[0] + ".block", strings[0]);
                        config.set("blocks." + strings[0] + ".permission", strings[1]);
                        Main.getInstance().saveConfig();
                        commandSender.sendMessage("§a" + message.get("message.command-correct"));
                    } else {
                        config.set("blocks." + strings[0] + ".block", strings[0]);
                        config.set("blocks." + strings[0] + ".permission", strings[1]);
                        Main.getInstance().saveConfig();
                        commandSender.sendMessage("§a" + message.get("message.block-changed"));
                    }

        return true;
    }
}
