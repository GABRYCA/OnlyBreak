package it.gabryca.onlybreak.commands;

import it.gabryca.onlybreak.OnlyBreak;
import it.gabryca.onlybreak.gui.OnlyBreakGUI;
import it.gabryca.onlybreak.onlybreak_api.OnlyBreakUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.Optional;

public class OBreakCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (args.length == 0){

            return commandList(sender);

        } else if (args[0].equalsIgnoreCase("add")) {

            String permission = null;

            if (args.length == 1) {

                sender.sendMessage(OnlyBreak.format(message.getString("Messages.Wrong_Format_Add")));
                return true;

            } else if (args.length == 3) {

                permission = args[2];

            }

            OnlyBreakUtil.get().addBlock(args[1], Optional.ofNullable(permission), sender);

        } else if (args[0].equalsIgnoreCase("disabled-worlds")){

            if (args.length == 1){
                sender.sendMessage(OnlyBreak.format("&7&bSubCommands:"));
                sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak disabled-worlds add <worldName>"));
                sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak disabled-worlds remove <worldName>"));
                return true;
            }

            if (args.length == 2){
                if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("remove")) {
                    sender.sendMessage(OnlyBreak.format(message.getString("Message.Disabled_World_Missing_Name")));
                    return true;
                }
                sender.sendMessage(OnlyBreak.format(message.getString("Message.Disabled_World_Invalid_Args")) + "[" + args[1] + "]");
                return true;
            }

            if (args.length == 3){
                if (args[1].equalsIgnoreCase("add")){

                    OnlyBreakUtil.get().addDisabledWorld(sender, args[2]);

                } else if (args[1].equalsIgnoreCase("remove")){

                    OnlyBreakUtil.get().removeDisabledWorld(sender, args[2]);

                } else {
                    sender.sendMessage(OnlyBreak.format(message.getString("Message.Disabled_World_Invalid_Args")) + "[" + args[1] + "]");
                }
            }

        } else if (args[0].equalsIgnoreCase("delete")){

            if (args.length == 1){

                sender.sendMessage(OnlyBreak.format(message.getString("Messages.Wrong_Format_Delete")));
                return true;

            }

            OnlyBreakUtil.get().blockDelete(args[1], sender);

        } else if (args[0].equalsIgnoreCase("GUI")){

            if (!(sender instanceof Player)){
                sender.sendMessage(OnlyBreak.format(message.getString("Messages.Console_Cant")));
                return true;
            }

            Player p = (Player) sender;

            OnlyBreakGUI gui = new OnlyBreakGUI(p);
            gui.open();

        } else if (args[0].equalsIgnoreCase("edit")){

            String permission = null;

            if (args.length == 1){

                sender.sendMessage(OnlyBreak.format(message.getString("Messages.Wrong_Format_Edit")));
                return true;

            } else if (args.length == 3){

                permission = args[2];

            }

            OnlyBreakUtil.get().editBlock(args[1], Optional.ofNullable(permission), sender);

        } else {

            return commandList(sender);

        }

        return true;
    }

    private boolean commandList(CommandSender sender) {
        sender.sendMessage(OnlyBreak.format("&7&bCommands:"));
        sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak"));
        sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak add <Block_Name - ID> <Optional - Permission>"));
        sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak delete <Block_Name - ID>"));
        sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak edit <Block_Name - ID> <New Permission>"));
        sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak GUI -> Open the GUI"));
        sender.sendMessage(OnlyBreak.format("&3&b - &3 /obreak disabled-worlds add/remove <worldName>"));
        return true;
    }
}
