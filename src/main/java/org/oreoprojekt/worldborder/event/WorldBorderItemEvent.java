package org.oreoprojekt.worldborder.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.oreoprojekt.worldborder.WorldBorder;

public class WorldBorderItemEvent implements Listener {
    String Prefix = new WorldBorder().Prefix;

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (item != null) {
            if (item.getType() == Material.BLAZE_ROD && item.getItemMeta().hasDisplayName()) {
                if (item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "☆마법지팡이☆") || item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "테스트용")) {
                    int count = item.getAmount();
                    if (player.getWorld().getEnvironment().equals(World.Environment.NETHER) || player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                        player.sendMessage(Prefix + "이곳에선 사용하실 수 없습니다!");
                    }
                    else {
                        for (int i = 0; i < count; i++) {
                            double sizenow = player.getWorld().getWorldBorder().getSize();
                            player.getWorld().getWorldBorder().setSize(sizenow + 6);
                            Bukkit.broadcastMessage(Prefix + ChatColor.AQUA + "월드가 확장되었습니다.");
                            player.getInventory().removeItem(item);
                        }
                    }
                }
            }
        }
    }
}
