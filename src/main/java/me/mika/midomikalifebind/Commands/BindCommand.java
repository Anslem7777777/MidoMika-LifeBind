package me.mika.midomikalifebind.Commands;

import me.mika.midomikalifebind.MidoMika_LifeBind;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.util.HashMap;

public class BindCommand extends LifeBindSubCommands{
    @Override
    public String getName() {
        return "bind";

    }

    @Override
    public String getDescription() {
        return "bind life to another player";

    }

    @Override
    public String getSyntax() {
        return "/lb bind <PlayerName>";

    }

    public static final HashMap<Player,Player> lifeBind = new HashMap<>();
    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1) {
            if (Bukkit.getPlayer(args[1]) != null) {
                if (!Bukkit.getPlayer(args[1]).equals(player)) {
                    // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
                    NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
                    PersistentDataContainer playerData = Bukkit.getPlayer(args[1]).getPersistentDataContainer();

                    if (playerData.has(key, PersistentDataType.STRING)) {
//                playerData.set(key, PersistentDataType.STRING, null);
                        player.sendMessage(ChatColor.RED + "Player already life bind with another player");

                    } else {
                        player.sendMessage(ChatColor.GREEN + "Request success sent to target");
                        lifeBind.put(player, null);
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.AQUA + "-----------------------------");
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.GOLD + player.getName() + ChatColor.YELLOW + " request life bind with you");
                        Bukkit.getPlayer(args[1]).sendMessage("=============================");

                        TextComponent acceptComponent = new TextComponent(String.format("         Accept"));
                        acceptComponent.setColor(net.md_5.bungee.api.ChatColor.GREEN);
                        acceptComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lb accept"));
                        acceptComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to accept life bind with " + player.getName()).color(net.md_5.bungee.api.ChatColor.AQUA).create()));

                        TextComponent rejectComponent = new TextComponent(String.format("         Reject"));
                        rejectComponent.setColor(net.md_5.bungee.api.ChatColor.RED);
                        rejectComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/lb reject"));
                        rejectComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click me to reject life bind with " + player.getName()).color(net.md_5.bungee.api.ChatColor.AQUA).create()));

                        Bukkit.getPlayer(args[1]).spigot().sendMessage(acceptComponent, rejectComponent);
                        Bukkit.getPlayer(args[1]).sendMessage(ChatColor.AQUA + "-----------------------------");

                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Please select another player not yourself");
                }
            }else {
                player.sendMessage(ChatColor.RED + "Player not found");
            }
        }else {
            player.sendMessage(ChatColor.RED + "Please select a player");

        }
    }
}