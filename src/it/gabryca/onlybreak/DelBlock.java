package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class DelBlock implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (!(commandSender.hasPermission(config.getString("RemoveBlockPermission")))){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-perm") + " [" + config.getString("RemoveBlockPermission") + "]");
            return true;
        }

        if (!(strings.length == 1)){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-format-delblock"));
            return true;
        }

        if (Material.getMaterial(strings[0]) == null){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-NotMaterial") + " [ " + strings[0] + " ]");
            return true;
        }

        if (config.getString("blocks." + strings[0]) == null){
            commandSender.sendMessage(ChatColor.RED + message.getString("message.NoBlocks"));
            return true;
        }

                        config.set("blocks." + strings[0] + ".block", null);
                        config.set("blocks." + strings[0] + ".permission", null);
                        config.set("blocks." + strings[0], null);
                        Main.getInstance().saveConfig();
                        commandSender.sendMessage("§a" + message.get("message.command-correct"));

        return true;
    }
}
