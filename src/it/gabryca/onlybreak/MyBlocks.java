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

        if (!(config.getBoolean("MyBlocks-Enabled"))){
            commandSender.sendMessage("§c" + message.getString("message.warn-disabled"));
            return true;
        }

            if (config.getBoolean("MyBlocks-Enable-Permission")) {

                if (!(commandSender.hasPermission(config.getString("MyBlocks-Permission")))){
                    commandSender.sendMessage("§4" + message.getString("message.warn-perm") + " §7[§c" + config.getString("MyBlocks-Permission") + "§7]");
                    return true;
                }

                if (!(commandSender instanceof Player)){
                    commandSender.sendMessage("§4" + message.getString("message.warn-NotAPlayer"));
                    return true;
                }

                Player p = (Player) commandSender;
                MyBlocksGUI gui = new MyBlocksGUI(p);
                gui.open();
            } else {

                if (!(commandSender instanceof Player)){
                    commandSender.sendMessage("§4" + message.getString("message.warn-NotAPlayer"));
                    return true;
                }

                Player p = (Player) commandSender;
                MyBlocksGUI gui = new MyBlocksGUI(p);
                gui.open();
                return true;
            }
            return true;
        }
}
