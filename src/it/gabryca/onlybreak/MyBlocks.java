package it.gabryca.onlybreak;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class MyBlocks implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();

        if (config.getBoolean("MyBlocks-Enabled")) {
            if (config.getBoolean("MyBlocks-Enable-Permission")) {
                if (commandSender.hasPermission(config.getString("MyBlocks-Permission"))) {
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        MyBlocksGUI gui = new MyBlocksGUI(p);
                        gui.open();
                    } else {
                        commandSender.sendMessage("§4" + message.getString("message.warn-NotAPlayer"));
                    }
                } else {
                    commandSender.sendMessage("§4" + message.getString("message.warn-perm") + " §7[§c" + config.getString("MyBlocks-Permission") + "§7]");
                }
            } else {
                if (commandSender instanceof Player) {
                    Player p = (Player) commandSender;
                    MyBlocksGUI gui = new MyBlocksGUI(p);
                    gui.open();
                } else {
                    commandSender.sendMessage("§4" + message.getString("message.warn-NotAPlayer"));
                }
            }
        } else {
            return true;
        }
            return true;
        }
}
