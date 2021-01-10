package it.gabryca.onlybreak.onlybreak_api;


import it.gabryca.onlybreak.OnlyBreak;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;

import java.util.Optional;

public class OnlyBreakUtil {

    private static OnlyBreakUtil instance;

    public OnlyBreakUtil(){}

    public static OnlyBreakUtil get() {
        if (instance == null) {
            instance = new OnlyBreakUtil();
        }
        return instance;
    }

    /**
     * This's used to add a block, you'll need the blockName, the blockPermission (Optional) and the sender
     * */
    public void addBlock(String blockName, Optional<String> blockPermission, CommandSender sender){

        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (!(sender.hasPermission(config.getString("Permissions.addBlockPermission")))){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.warn-perm") + " [" + config.getString("Permissions.addBlockPermission") + "]"));
            return;
        }

        if (blockChecker(blockName, blockPermission, sender, config, message)) return;
        sender.sendMessage(OnlyBreak.format(message.getString("Messages.command-success-block") + " [ " + blockName + " ]"));
    }

    /**
     * This's used to edit an already added block using the blockName, a new blockPermission (always Optional or will use default) and the sender
     * */
    public void editBlock(String blockName, Optional<String> blockPermission, CommandSender sender){

        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (!(sender.hasPermission(config.getString("Permissions.editBlockPermission")))){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.warn-perm") + " [" + config.getString("Permissions.editBlockPermission") + "]"));
            return;
        }

        if (blockNameCheck(blockName, sender, config, message)) return;

        if (blockChecker(blockName, blockPermission, sender, config, message)) return;
        sender.sendMessage(OnlyBreak.format(message.getString("Messages.command-success-block-edit") + " [ " + blockName + " ]"));

    }

    private boolean blockChecker(String blockName, Optional<String> blockPermission, CommandSender sender, Configuration config, Configuration message) {

        if (Material.getMaterial(blockName) == null){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.warn-notMaterial") + " [ " + blockName + " ]"));
            return true;
        }

        String permission = config.getString("Permissions.defaultBreakBlockFormat") + blockName;

        if (blockPermission.isPresent()){
            permission = blockPermission.get();
        }

        config.set("blocks." + blockName + ".block", blockName);
        config.set("blocks." + blockName + ".permission", permission);
        OnlyBreak.getInstance().saveConfig();
        return false;
    }

    /**
     * Delete the block from the config, you'll need the blockName and the sender
     * */
    public void blockDelete(String blockName, CommandSender sender){

        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (!(sender.hasPermission(config.getString("Permissions.deleteBlockPermission")))){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.warn-perm") + " [" + config.getString("Permissions.deleteBlockPermission") + "]"));
            return;
        }

        if (blockNameCheck(blockName, sender, config, message)) return;

        config.set("blocks." + blockName, null);
        OnlyBreak.getInstance().saveConfig();
        sender.sendMessage(OnlyBreak.format(message.getString("Messages.Success_Block_Delete") + " [ " + blockName + " ]"));
    }

    private boolean blockNameCheck(String blockName, CommandSender sender, Configuration config, Configuration message) {
        if (blockName == null){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.Missing_Block_Name")));
            return true;
        }

        String blockNameCheck;

        try {
            blockNameCheck = config.getString("blocks." + blockName + ".block");
        } catch (NullPointerException e){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.Block_Not_Found")) + " [ " +  blockName + " ]");
            return true;
        }

        if (blockNameCheck == null){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.Block_Not_Found")) + " [ " +  blockName + " ]");
            return true;
        }
        return false;
    }

    public void addDisabledWorld(CommandSender sender, String worldName){

        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (!sender.hasPermission(config.getString("Permissions.addDisabledWorld"))){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.warn-perm") + "[" + config.getString("Permissions.addDisabledWorld") + "]"));
            return;
        }

        config.set("disabled-worlds." + worldName + ".name", worldName);
        OnlyBreak.getInstance().saveConfig();
        sender.sendMessage(OnlyBreak.format(message.getString("Message.Disabled_World_Add_Success")) + "[" + worldName + "]");
    }

    public void removeDisabledWorld(CommandSender sender, String worldName){

        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (!sender.hasPermission(config.getString("Permissions.removeDisabledWorld"))){
            sender.sendMessage(OnlyBreak.format(message.getString("Messages.warn-perm") + "[" + config.getString("Permissions.removeDisabledWorld") + "]"));
            return;
        }

        if (!config.getString("disabled-worlds." + worldName + ".name").equalsIgnoreCase(worldName)){
            sender.sendMessage(OnlyBreak.format(message.getString("Message.Disabled_World_Remove_Not_Found")));
            return;
        }

        config.set("disabled-worlds." + worldName + ".name", null);
        OnlyBreak.getInstance().saveConfig();
        sender.sendMessage(OnlyBreak.format(message.getString("Message.Disabled_World_Remove_Success")) + "[" + worldName + "]");
    }
}
