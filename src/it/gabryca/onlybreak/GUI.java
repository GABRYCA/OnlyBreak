package it.gabryca.onlybreak;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GUI {

    List<String> block = Main.getInstance().getConfig().getStringList("blocks");
    String[] blocks = block.toArray(new String[0]);
    List<String> permission = Main.getInstance().getConfig().getStringList("permissions");
    String[] permissions = permission.toArray(new String[0]);
    String NotValidBlockID = Main.getInstance().getConfig().getString("message.NotValidBlockID");
    int x = 0;
    int y = Array.getLength(blocks);

    private Player p;

    public GUI(Player p){
        this.p = p;
    }

    public ItemStack createButton(Material id, int amount, List<String> lore, String display) {

        ItemStack item = new ItemStack(id, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display);
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    public void open() {

        Inventory inv = Bukkit.createInventory(null,27,"§7Blocchi");

        while (x < y) {
            if(!(Material.getMaterial(blocks[x]) == null)) {
                List<String> lore = new ArrayList<String>();
                lore.add("§2" + Main.getInstance().getConfig().getString("Block") + ": §7" + blocks[x]);
                lore.add("§2" + Main.getInstance().getConfig().getString("Permission") + ": §c" + permissions[x]);
                inv.addItem(createButton(Material.valueOf(String.valueOf(blocks[x])), 1, lore, "§6" + blocks[x]));
            } else {
                List<String> lore = new ArrayList<String>();
                lore.add("§c" + Main.getInstance().getConfig().getString("message.warn-NotMaterialAdvice"));
                lore.add("§c[ " + blocks[x] + " , " + permissions[x] + " ]" );
                inv.addItem(createButton(Material.valueOf(NotValidBlockID), 1, lore, "§c" + Main.getInstance().getConfig().getString("message.warn-NotMaterial")));
            }
        x++;
        }

        this.p.openInventory(inv);
    }

}
