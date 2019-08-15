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

        if (commandSender.hasPermission(config.getString("RemoveBlockPermission"))){
            if (strings.length == 1){
                if(!(Material.getMaterial(strings[0]) == null)) {
                    if (!(config.getString("blocks." + strings[0]) == null)){
                        config.set("blocks." + strings[0] + ".block", null);
                        config.set("blocks." + strings[0] + ".permission", null);
                        config.set("blocks." + strings[0], null);
                        Main.getInstance().saveConfig();
                        commandSender.sendMessage("§a" + message.get("message.command-correct"));
                    } else {
                        commandSender.sendMessage(ChatColor.RED + message.getString("message.NoBlocks"));
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-NotMaterial") + " [ " + strings[0] + " ]");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-format-delblock"));
            }
        } else {
            commandSender.sendMessage(ChatColor.RED + message.getString("message.warn-perm") + " [" + config.getString("RemoveBlockPermission") + "]");
        }
        return true;
    }
}
