package it.gabryca.onlybreak;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class GUI {

    Configuration config = Main.getInstance().getConfig();
    Configuration message = Main.getMessages();
    String NotValidBlockID = config.getString("message.NotValidBlockID");
    int dimension = 0;

    private Player p;

    GUI(Player p){
        this.p = p;
    }

    private ItemStack createButton(Material id, int amount, List<String> lore, String display) {

        ItemStack item = new ItemStack(id, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    void open() {

        if (config.getConfigurationSection("blocks") == null) {
            p.sendMessage("§c" + message.getString("Message.EmptyGUI"));
        } else {
            Set<String> shops = config.getConfigurationSection("blocks").getKeys(false);
            int num = shops.size();
            while (dimension < num + 8) {
                dimension = dimension + 9;
            }
            Inventory inv = Bukkit.createInventory(null, dimension, "§7Block");
            for (String key : shops) {
                if (!(Material.getMaterial(config.getString("blocks." + key + ".block")) == null)) {
                    List<String> lore = new ArrayList<String>();
                    lore.add("§2" + config.getString("Block") + ": §7" + config.getString("blocks." + key + ".block"));
                    lore.add("§2" + config.getString("Permission") + ": §c" + config.getString("blocks." + key + ".permission"));
                    lore.add("§c" + message.getString("Message.RightClickToDelete"));
                    inv.addItem(createButton(Material.valueOf(String.valueOf(config.getString("blocks." + key + ".block"))), 1, lore, "§6" + config.getString("blocks." + key + ".block")));
                } else {
                    List<String> lore = new ArrayList<String>();
                    lore.add("§2" + config.getString("Block") + ": §7" + config.getString("blocks." + key + ".block"));
                    lore.add("§2" + config.getString("Permission") + ": §c" + config.getString("blocks." + key + ".permission"));
                    lore.add("§c" + message.getString("Message.RightClickToDelete"));
                    inv.addItem(createButton(Material.valueOf(NotValidBlockID), 1, lore, "§c" + config.getString("message.warn-NotMaterial")));
                }
            }
            this.p.openInventory(inv);
        }
    }

}
