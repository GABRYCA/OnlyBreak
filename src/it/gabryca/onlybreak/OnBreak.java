package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnBreak implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (e.getCurrentItem() == null)
            return;

        if (e.getClickedInventory().getTitle().equals("§7Blocks")){
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void OnBreak2(BlockBreakEvent e) {
        Material block = Material.valueOf(String.valueOf(e.getBlock().getType()));
        Configuration config = Main.getInstance().getConfig();
        Player p = e.getPlayer();
        if (!(config.getString("blocks." + block) == null)) {
            if (!(config.getString("blocks." + block + ".block") == null)) {
                if (!(p.hasPermission(config.getString("blocks." + block + ".permission")))) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + config.getString("message.warn-perm-block"));
                }
            }
        }
    }
}
