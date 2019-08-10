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

            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                MyBlocksGUI gui = new MyBlocksGUI(p);
                gui.open();
            } else {
                commandSender.sendMessage("§4" + config.getString("message.warn-NotAPlayer"));
            }

        return true;
    }

}
