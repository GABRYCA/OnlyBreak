package it.gabryca.onlybreak;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlockGUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender.hasPermission(Main.getInstance().getConfig().getString("BlockGUIPermission"))) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                GUI gui = new GUI(p);
                gui.open();
            } else {
                commandSender.sendMessage("§4" + Main.getInstance().getConfig().getString("message.warn-NotAPlayer"));
            }
        } else {
            commandSender.sendMessage("§c" + Main.getInstance().getConfig().getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockGUIPermission") + " ]");
        }

        return true;
    }
}
