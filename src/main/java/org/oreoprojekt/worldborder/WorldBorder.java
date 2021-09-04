package org.oreoprojekt.worldborder;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.oreoprojekt.worldborder.command.WorldBorderCommand;
import org.oreoprojekt.worldborder.event.WorldBorderItemEvent;

public final class WorldBorder extends JavaPlugin {
    public static String Prefix = ChatColor.YELLOW + "[WorldBorder] ";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "hello, WorldBorder Plugin On");
        getCommand("wb").setExecutor(new WorldBorderCommand());
        getServer().getPluginManager().registerEvents(new WorldBorderItemEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "good bye, WorldBorder Plugin Off");
    }
}
