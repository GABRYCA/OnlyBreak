package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnBreak implements Listener {

    @EventHandler
    public void OnBreak2(BlockBreakEvent e){
        Player p = e.getPlayer();
        int x = 0;
        List<String> block = Main.getInstance().getConfig().getStringList("blocks");
        String[] blocks = block.toArray(new String[0]);
        List<String> permission = Main.getInstance().getConfig().getStringList("permissions");
        String[] permissions = permission.toArray(new String[0]);
        int y = Array.getLength(blocks);
        while(x < y){
            if (e.getBlock().getType() == Material.valueOf(blocks[x])) {
                if (!p.hasPermission(permissions[x]) && e.getBlock().getType() == Material.valueOf(blocks[x])) {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm-block"));
                }
            }
        x++;
        }
    }
}
