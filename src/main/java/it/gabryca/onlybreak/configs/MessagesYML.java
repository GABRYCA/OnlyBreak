package it.gabryca.onlybreak.configs;

import it.gabryca.onlybreak.OnlyBreak;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesYML {

    private FileConfiguration conf;

    public MessagesYML(){

        // Filepath
        File file = new File(OnlyBreak.getInstance().getDataFolder() + "/Messages.yml");

        // Everything's here
        values();

        // Get the final config
        conf = YamlConfiguration.loadConfiguration(file);
    }

    private void dataConfig(String path, String string){

        // Filepath
        File file = new File(OnlyBreak.getInstance().getDataFolder() + "/Messages.yml");

        // Check if the config exists
        if(!file.exists()){
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                conf.set(path, OnlyBreak.format(string));
                conf.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                boolean newValue = false;
                conf = YamlConfiguration.loadConfiguration(file);
                if (getFile().getString(path) == null){
                    conf.set(path, OnlyBreak.format(string));
                    newValue = true;
                }
                if (newValue) {
                    conf.save(file);
                }
            } catch (IOException e2){
                e2.printStackTrace();
            }
        }

        // Get the final config
        conf = YamlConfiguration.loadConfiguration(file);

    }

    private void values(){
        dataConfig("Messages.warn-perm", "&cSorry but you don't have the permission to use that!");
        dataConfig("Messages.warn-perm-block", "&cSorry, you can't break this block!");
        dataConfig("Messages.warn-notMaterial", "&cSorry but that block-material doesn't exists!");
        dataConfig("Messages.command-success-block", "&aBlock added with success!");
        dataConfig("Messages.command-success-block", "&aBlock edited with success!");
        dataConfig("Messages.Block_Not_Found", "&cBlock not found in the config!");
        dataConfig("Messages.Missing_Block_Name", "&cPlease write a blockName!");
        dataConfig("Messages.Success_Block_Delete", "&aBlock deleted with success.");
        dataConfig("Messages.Wrong_Format_Add", "&cWrong format, use something like this: /obreak add COAL_ORE obreak.break.COAL_ORE .");
        dataConfig("Messages.Wrong_Format_Edit", "&cWrong format, use something like this: /obreak edit COAL_ORE obreak.break.COAL_ORE .");
        dataConfig("Messages.Wrong_Format_Delete", "&cWrong format, use something like this: /obreak delete COAL_ORE.");
        dataConfig("Messages.Console_Cant", "&cSorry but you can't run this command from the console!");
        dataConfig("Messages.GUI_No_Blocks", "&cThere aren't blocks added, please add one!");
        dataConfig("Messages.GUI_Too_Many_Blocks", "&cThere're too many blocks and GUI doesn't support more than 54!");
        dataConfig("Messages.GUI_Open_Success", "&aBlocks GUI opened with success!");
    }

    public FileConfiguration getFile(){
        return conf;
    }

}
