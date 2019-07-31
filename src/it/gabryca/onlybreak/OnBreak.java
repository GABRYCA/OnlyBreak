package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnBreak implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){

        if (e.getCurrentItem() == null)
            return;

        if (e.getClickedInventory().getTitle().equals("§7Blocchi")){
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void OnBreak2(BlockBreakEvent e){
        Configuration config = Main.getInstance().getConfig();
        Player p = e.getPlayer();
        int x = 0;
        List<String> block = config.getStringList("blocks");
        String[] blocks = block.toArray(new String[0]);
        List<String> permission = config.getStringList("permissions");
        String[] permissions = permission.toArray(new String[0]);
        int y = Array.getLength(blocks);
        int perm = Array.getLength(permissions);
        while(x < y){
            if (e.getBlock().getType() == Material.valueOf(blocks[x])) {
                if (!p.hasPermission(permissions[x]) && e.getBlock().getType() == Material.valueOf(blocks[x])) {
                    if (y == perm) {
                        e.setCancelled(true);
                        p.sendMessage(ChatColor.RED + config.getString("message.warn-perm-block"));
                    } else {
                        p.sendMessage("§c" + config.getString("message.PermissionsBlocksDismatch") + " [ " + y + " , " + perm + " ]");
                        p.sendMessage(ChatColor.RED + config.getString("message.warn-perm-block"));
                        e.setCancelled(true);
                    }
                }
            }
        x++;
        }
    }
}
