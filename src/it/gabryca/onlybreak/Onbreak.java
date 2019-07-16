package it.gabryca.onlybreak;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Onbreak implements Listener {

    @EventHandler
    public void CoalOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.coalore")) && e.getBlock().getType() == Material.COAL_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void IronOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.ironore")) && e.getBlock().getType() == Material.IRON_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void RedStoneOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.redstoneore")) && e.getBlock().getType() == Material.REDSTONE_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void LapisOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.lapisore")) && e.getBlock().getType() == Material.LAPIS_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void GoldOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.goldore")) && e.getBlock().getType() == Material.GOLD_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void DiamondOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.diamondore")) && e.getBlock().getType() == Material.DIAMOND_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void EmeraldOre(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.emeraldore")) && e.getBlock().getType() == Material.EMERALD_ORE) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void CoalBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.coalblock")) && e.getBlock().getType() == Material.COAL_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void IronBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.ironblock")) && e.getBlock().getType() == Material.IRON_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void RedstoneBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.redstoneblock")) && e.getBlock().getType() == Material.REDSTONE_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void LapisBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.lapisblock")) && e.getBlock().getType() == Material.LAPIS_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void GoldBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.goldblock")) && e.getBlock().getType() == Material.GOLD_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void DiamondBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.diamondblock")) && e.getBlock().getType() == Material.DIAMOND_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }

    }

    @EventHandler
    public void EmeraldBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(Main.getInstance().getConfig().getString("permissions.emeraldblock")) && e.getBlock().getType() == Material.EMERALD_BLOCK) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + Main.getInstance().getConfig().getString("message.warn-perm"));
        }
    }
}
