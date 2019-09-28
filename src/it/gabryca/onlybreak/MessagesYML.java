package it.gabryca.onlybreak;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

class MessagesYML {

    private File file = new File(Main.getInstance().getDataFolder()+"/messages.yml");
    private FileConfiguration conf;

    MessagesYML() {
       if(!file.exists()){
           try {
               file.createNewFile();
               conf = YamlConfiguration.loadConfiguration(file);
               conf.createSection("message");
               conf.set("message.warn-perm-block", "You need to be of an higher rank or you don't have the permission");
               conf.set("message.warn-perm", "You don't have the permissions to do this command");
               conf.set("message.warn-format", "Invalid format, use /addblock <BLOCK_NAME> <break.permission>, for example /addblock COAL_ORE break.coalore");
               conf.set("message.warn-format-delblock", "Invalid format, please use /delblock <Block_Name>, for example /delblock COAL_ORE");
               conf.set("message.warn-disabled", "This function it's disabled from the config");
               conf.set("message.command-correct", "Command executed with success!");
               conf.set("message.block-changed", "Block changed with success");
               conf.set("message.warn-NotAPlayer", "You aren't a player!");
               conf.set("message.warn-NotMaterial", "That material doesn't exist");
               conf.set("message.warn-NotMaterialAdvice", "Check the config and remove the block");
               conf.set("message.NotValidBlockID", "BARRIER");
               conf.set("message.NoBlocks", "That block isn't in the config");
               conf.set("Message.EmptyGUI", "There aren't blocks added, so the GUI won't be open as it is empty");
               conf.save(file);
           } catch (IOException e) {
               e.printStackTrace();
           }
           return;
       }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    FileConfiguration getFile(){
        return conf;
    }

}
