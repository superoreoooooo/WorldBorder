package org.oreoprojekt.worldborder.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.oreoprojekt.worldborder.WorldBorder;

import java.util.Arrays;

public class WorldBorderCommand implements CommandExecutor {
    String Prefix = new WorldBorder().Prefix;

    public boolean checkPermission(CommandSender sender, String permission) {
        if (sender.hasPermission(permission)) {
            return true;
        }
        String message = Prefix + ChatColor.RED + "you do not have any of permissions to do that.";
        sender.sendMessage(message);
        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {  //명령어 실행 시
        if (sender instanceof Player) {  //명령어 사용자가 플레이어인 경우
            if (!checkPermission(sender,"administrator")){
                sender.sendMessage(Prefix + "권한이 없습니다.");
                return false;
            }
            Player player = (Player) sender;
            try {
                if (args[0].equalsIgnoreCase("reset")) {
                    Bukkit.broadcastMessage(Prefix + ChatColor.RED + "경계가 초기화 되었습니다.");
                    player.getWorld().getWorldBorder().setSize(100);
                }
                else if (args[0].equalsIgnoreCase("now")) {
                    double size = player.getWorld().getWorldBorder().getSize();
                    player.sendMessage(Prefix + ChatColor.YELLOW + "현재 월드의 크기는 " + (int)size + "x" + (int)size + "칸 입니다.");
                    player.sendMessage(Prefix + ChatColor.YELLOW + "현재 월드의 경계는 " + (int)((size-100)/2) + "번 확장되었습니다.");
                }
                else if (args[0].equalsIgnoreCase("give")) {
                    String target = "";
                    String cnt = "";
                    try {
                        target = args[1];
                        cnt = args[2];

                        int count = Integer.parseInt(cnt);
                        Player t = Bukkit.getServer().getPlayer(target);
                        assert t != null;

                        Material type = Material.matchMaterial("BLAZE_ROD");
                        ItemStack item = new ItemStack(type, count);
                        ItemMeta meta = (ItemMeta) item.getItemMeta();
                        meta.setDisplayName(ChatColor.DARK_PURPLE + "☆마법지팡이☆");
                        meta.setLore(Arrays.asList(ChatColor.YELLOW + "전설의 마법 지팡이다.", ChatColor.AQUA + "인벤토리에서 클릭하면 월드의 경계가 늘어난다."));
                        item.setItemMeta(meta);
                        t.getInventory().addItem(item);
                    }
                    catch (Exception e) {
                        player.sendMessage(Prefix + ChatColor.GREEN + "사용법 : /wb give 유저이름 갯수");
                    }
                }
                else if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(ChatColor.YELLOW + "/wb reset : 초기화");
                    player.sendMessage(ChatColor.YELLOW + "/wb now : 현재 칸수");
                    player.sendMessage(ChatColor.YELLOW + "/wb give : 마법지팡이 얻기");
                }
            }
            catch (Exception e) {
                player.sendMessage(ChatColor.YELLOW + "/wb reset : 초기화");
                player.sendMessage(ChatColor.YELLOW + "/wb now : 현재 칸수");
                player.sendMessage(ChatColor.YELLOW + "/wb give : 마법지팡이 얻기");
            }
            return true;
        }
        else if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage(Prefix + "콘솔에서는 이 명령어를 실행할 수 없습니다.");
            return false;
        }
        return true;
    }
}
