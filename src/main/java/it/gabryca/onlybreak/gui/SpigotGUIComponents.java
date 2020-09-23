package it.gabryca.onlybreak.gui;

import it.gabryca.onlybreak.OnlyBreak;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class SpigotGUIComponents {

    // createButton method (create a button for the GUI - item)

    protected ItemStack createButton(Material id, int amount, List<String> lore, String display) {

        ItemStack item = new ItemStack(id, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(display);
        try {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        } catch (NoClassDefFoundError ignored){}
        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    // createLore method (create a lore for the button)
    protected List<String> createLore(String... lores) {
        List<String> results = new ArrayList<>();
        for (String lore : lores) {
            results.add(OnlyBreak.format(lore));
        }
        return results;
    }

}
