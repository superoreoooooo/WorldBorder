package org.oreoprojekt.worldborder.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static org.oreoprojekt.worldborder.WorldBorder.Prefix;

public class WorldBorderManager {
    public void setWorldBorder(Player player, ItemStack item) {
        int count = item.getAmount();
        if (count >= 2) {
            if (player.getWorld().getEnvironment().equals(World.Environment.NETHER) || player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                player.sendMessage(Prefix + "이곳에선 사용하실 수 없습니다!");
            } else {
                double sizenow = player.getWorld().getWorldBorder().getSize();
                player.getWorld().getWorldBorder().setSize(sizenow + 6 * count);
                Bukkit.broadcastMessage(Prefix + ChatColor.AQUA + count + "번 월드가 확장되었습니다.");
                player.getInventory().removeItem(item);
            }
        } else {
            double sizenow = player.getWorld().getWorldBorder().getSize();
            player.getWorld().getWorldBorder().setSize(sizenow + 6);
            Bukkit.broadcastMessage(Prefix + ChatColor.AQUA + "월드가 확장되었습니다.");
            player.getInventory().removeItem(item);
        }
    }
}
