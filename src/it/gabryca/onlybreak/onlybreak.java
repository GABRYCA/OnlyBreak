package it.gabryca.onlybreak;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

public class onlybreak implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (commandSender.hasPermission(config.getString("OnlyBreak-command-permission"))){
            commandSender.sendMessage("§7§lCommands:");
            commandSender.sendMessage("§7 - §b /AddBlock");
            commandSender.sendMessage("§7 - §b /BlockList");
            commandSender.sendMessage("§7 - §b /BlockGUI");
            commandSender.sendMessage("§7 - §b /DelBlock");
            commandSender.sendMessage("§7 - §b /MyBlocks");
            commandSender.sendMessage("§7 - §b /Onlybreak");
        } else {
            commandSender.sendMessage("§4" + message.getString("message.warn-perm") + " §7[§c" + config.getString("OnlyBreak-command-permission") + "§7]");
        }

        return true;
    }
}
