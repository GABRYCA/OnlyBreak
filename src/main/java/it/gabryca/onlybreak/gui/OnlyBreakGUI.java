package it.gabryca.onlybreak.gui;

import it.gabryca.onlybreak.OnlyBreak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class OnlyBreakGUI extends SpigotGUIComponents{

    private final Player p;
    private int dimension = 9;

    public OnlyBreakGUI(Player p){
        this.p = p;
    }

    public void open() {

        // Create the inventory and set up the owner, dimensions or number of slots, and title
        Inventory inv = Bukkit.createInventory(null, dimension, OnlyBreak.format("&3OnlyBreak"));

        Configuration messages = OnlyBreak.getMessages();
        Configuration config = OnlyBreak.getInstance().getConfig();

        if (guiBuilder(inv, messages, config)) return;

        // If the inventory is empty
        if (dimension == 0){
            p.sendMessage(OnlyBreak.format(messages.getString("Messages.GUI_No_Blocks")));
            p.closeInventory();
            return;
        }

        // If the dimension's too big, don't open the GUI
        if (dimension > 54){
            p.sendMessage(OnlyBreak.format(messages.getString("Messages.GUI_Too_Many_Blocks")));
            p.closeInventory();
            return;
        }

        // Open the inventory
        this.p.openInventory(inv);
        OnBreakAndListeners.get().addToGUIBlocker(p);
        p.sendMessage(OnlyBreak.format(messages.getString("Messages.GUI_Open_Success")));
    }

    private boolean guiBuilder(Inventory inv, Configuration messages, Configuration config) {
        try {
            buttonsSetup(inv, messages, config);
        } catch (NullPointerException ex){
            p.sendMessage(OnlyBreak.format("&cThere's a null value [broken]"));
            ex.printStackTrace();
            return true;
        }
        return false;
    }

    private void buttonsSetup(Inventory inv, Configuration messages, Configuration config) {

        int blocks = 0;

        List<String> loreButton = createLore(
                "&cShift + Right click to delete.");

        try {
            for (String block : config.getConfigurationSection("blocks").getKeys(false)) {
                    blocks++;
                    loreButton.add(OnlyBreak.format("&3Permission: - " + config.getString("blocks." + block + ".permission")));
                    ItemStack blockButton = createButton(Material.valueOf(config.getString("blocks." + block + ".block")), 1, loreButton, OnlyBreak.format("&3" + config.getString("blocks." + block  + ".block")));
                    inv.addItem(blockButton);
            }
        } catch (NullPointerException e){
            dimension = 0;
            return;
        }

        // Get the dimensions and if needed increases them
        dimension = (int) Math.ceil(blocks / 9D) * 9;

    }

}
