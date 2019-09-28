package it.gabryca.onlybreak;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class BlockGUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Configuration config = Main.getInstance().getConfig();

        if (!(commandSender.hasPermission(config.getString("BlockGUIPermission")))){
            commandSender.sendMessage("§c" + config.getString("message.warn-perm") + " [ " + config.getString("BlockGUIPermission") + " ]");
            return true;
        }

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("§4" + config.getString("message.warn-NotAPlayer"));
            return true;
        }

                    Player p = (Player) commandSender;
                    GUI gui = new GUI(p);
                    gui.open();

        return true;
    }
}
