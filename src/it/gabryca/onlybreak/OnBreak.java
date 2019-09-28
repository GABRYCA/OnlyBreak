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

        if (e.getView().getTitle().equals("§7Block")){

            if (e.getCurrentItem() == null){
                return;
            }

            if (!(e.isRightClick())){
                return;
            }

            Player p = (Player) e.getWhoClicked();
            Configuration config = Main.getInstance().getConfig();
            Configuration message = Main.getMessages();
            if (e.getCurrentItem().hasItemMeta()){
                String block = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
                config.set("blocks." + block, null);
                config.set("blocks", null);
                Main.getInstance().saveConfig();
                p.closeInventory();
                p.sendMessage("§a" + message.getString("Message.BlockDeletedSuccess"));
            }

        }

    }

    @EventHandler
    public void OnBreak2(BlockBreakEvent e) {
        Material block = Material.valueOf(String.valueOf(e.getBlock().getType()));
        Configuration config = Main.getInstance().getConfig();
        Configuration message = Main.getMessages();
        Player p = e.getPlayer();

        if (config.getString("blocks." + block) == null){
            return;
        }

        if (config.getString("blocks." + block + ".block") == null){
            return;
        }

        if (p.hasPermission(config.getString("blocks." + block + ".permission"))){
            return;
        }

        e.setCancelled(true);
        p.sendMessage(ChatColor.RED + message.getString("message.warn-perm-block"));
        return;
    }
}
