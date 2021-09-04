package org.oreoprojekt.worldborder.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.oreoprojekt.worldborder.manager.WorldBorderManager;

import static org.oreoprojekt.worldborder.WorldBorder.Prefix;

public class WorldBorderItemEvent implements Listener {
    WorldBorderManager manager = new WorldBorderManager();

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (item != null) {
            if (item.getType() == Material.BLAZE_ROD && item.getItemMeta().hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "☆마법지팡이☆")) {
                    manager.setWorldBorder(player, item);
                }
            }
        }
    }

    @EventHandler
    public void onItemuse(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = e.getItem();

        if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
            if (item != null) {
                if (item.getType() == Material.BLAZE_ROD && item.getItemMeta().hasDisplayName()) {
                    if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "☆마법지팡이☆")) {
                        manager.setWorldBorder(player, item);
                    }
                }
            }
        }
    }
}
