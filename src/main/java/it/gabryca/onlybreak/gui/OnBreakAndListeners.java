package it.gabryca.onlybreak.gui;

import it.gabryca.onlybreak.OnlyBreak;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.List;

public class OnBreakAndListeners implements Listener {

    private boolean isDisabledWorld(BlockBreakEvent e){

        String worldName = e.getBlock().getWorld().getName();
        Configuration config = OnlyBreak.getInstance().getConfig();

        if (config.getString("disabled-worlds." + worldName + ".name") != null){
            return config.getString("disabled-worlds." + worldName + ".name").equalsIgnoreCase(worldName);
        }

        return false;
    }

    @EventHandler
    public void OnBreak2(BlockBreakEvent e) {

        if (isDisabledWorld(e)) return;

        Material block = e.getBlock().getType();
        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();
        Player p = e.getPlayer();

        if (config.getString("blocks." + block) == null){
            return;
        }

        String blockNameCheck;

        try {
            blockNameCheck = config.getString("blocks." + block.name() + ".block");
        } catch (NullPointerException ex){
            return;
        }

        if (blockNameCheck == null){
            return;
        }

        if (p.hasPermission(config.getString("blocks." + block + ".permission"))){
            return;
        }

        e.setCancelled(true);
        p.sendMessage(OnlyBreak.format("&c" + message.getString("Messages.warn-perm-block")));
    }

    private static OnBreakAndListeners instance;
    public static List<String> activeGui = new ArrayList<>();

    public OnBreakAndListeners(){}

    public static OnBreakAndListeners get() {
        if (instance == null) {
            instance = new OnBreakAndListeners();
        }
        return instance;
    }

    @EventHandler
    public void onGuiClosing(InventoryCloseEvent e){

        Player p = (Player) e.getPlayer();

        activeGui.remove(p.getName());
    }

    public void addToGUIBlocker(Player p){
        if(!activeGui.contains(p.getName()))
            activeGui.add(p.getName());
    }


    // Cancel the events of the active GUI opened from the player
    private void activeGuiEventCanceller(Player p, InventoryClickEvent e){
        if(activeGui.contains(p.getName())) {
            e.setCancelled(true);
        }
    }

    // InventoryClickEvent
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Configuration config = OnlyBreak.getInstance().getConfig();
        Configuration message = OnlyBreak.getMessages();

        if (e.getCurrentItem() == null) {
            return;
        }

        if (!(e.getCurrentItem().hasItemMeta())) {
            return;
        }

        // If you click an empty slot, this should avoid the error.
        // Also if there is no button that was clicked, then it may not be a Prison GUI on click event?
        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR ||
                e.getCurrentItem().getItemMeta() == null || !e.getCurrentItem().hasItemMeta() ||
                e.getCurrentItem().getItemMeta().getDisplayName() == null) {
            activeGuiEventCanceller(p, e);
            return;
        } else {
            e.getCurrentItem().getItemMeta().getDisplayName();
        }

        // Get action of the Inventory from the event
        InventoryAction action = e.getAction();

        // If an action equals one of these, and the inventory is open from the player equals
        // one of the Prison Title, it'll cancel the event
        if (action.equals(InventoryAction.MOVE_TO_OTHER_INVENTORY) || action.equals(InventoryAction.HOTBAR_SWAP) ||
                action.equals(InventoryAction.HOTBAR_MOVE_AND_READD) || action.equals(InventoryAction.NOTHING) ||
                action.equals(InventoryAction.CLONE_STACK) || action.equals(InventoryAction.COLLECT_TO_CURSOR) ||
                action.equals(InventoryAction.DROP_ONE_SLOT) || action.equals(InventoryAction.DROP_ONE_CURSOR) ||
                action.equals(InventoryAction.DROP_ALL_SLOT) || action.equals(InventoryAction.DROP_ALL_CURSOR) ||
                action.equals(InventoryAction.PICKUP_ALL) || action.equals(InventoryAction.PICKUP_HALF) ||
                action.equals(InventoryAction.PICKUP_ONE) || action.equals(InventoryAction.PICKUP_SOME) ||
                action.equals(InventoryAction.PLACE_ALL) || action.equals(InventoryAction.PLACE_ONE) ||
                action.equals(InventoryAction.PLACE_SOME) || action.equals(InventoryAction.SWAP_WITH_CURSOR) ||
                action.equals(InventoryAction.UNKNOWN)) {
            activeGuiEventCanceller(p, e);
        }

        String title = e.getView().getTitle().substring(2);
        if (title == null){
            title = e.getInventory().getTitle().substring(2);
        }

        String shop = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
        String[] data = shop.split(" ");

        if(activeGui.contains(p.getName())) {
            e.setCancelled(true);
        }

        if (title.equalsIgnoreCase("OnlyBreak")) {

            if (e.isRightClick() && e.isShiftClick()){
                Bukkit.dispatchCommand(p, "obreak delete " + data[0]);
            }

            e.setCancelled(true);

            p.closeInventory();
            OnlyBreakGUI gui = new OnlyBreakGUI(p);
            gui.open();
        }
    }

}
