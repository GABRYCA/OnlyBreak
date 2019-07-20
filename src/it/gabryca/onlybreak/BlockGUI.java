package it.gabryca.onlybreak;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.List;

public class BlockGUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        List<String> blocks = Main.getInstance().getConfig().getStringList("blocks");
        List<String> permissions = Main.getInstance().getConfig().getStringList("permissions");
        String[] block = blocks.toArray(new String[0]);
        String[] permission = permissions.toArray(new String[0]);
        int x = Array.getLength(block);
        int y = Array.getLength(permission);

        if (commandSender.hasPermission(Main.getInstance().getConfig().getString("BlockGUIPermission"))) {
            if (commandSender instanceof Player) {
                if (x == y) {
                    Player p = (Player) commandSender;
                    GUI gui = new GUI(p);
                    gui.open();
                } else {
                    commandSender.sendMessage("§c" + Main.getInstance().getConfig().getString("message.PermissionsBlocksDismatch") + " [ " + x + " , " + y + " ]");
                }
            } else {
                commandSender.sendMessage("§4" + Main.getInstance().getConfig().getString("message.warn-NotAPlayer"));
            }
        } else {
            commandSender.sendMessage("§c" + Main.getInstance().getConfig().getString("message.warn-perm") + " [ " + Main.getInstance().getConfig().getString("BlockGUIPermission") + " ]");
        }

        return true;
    }
}
