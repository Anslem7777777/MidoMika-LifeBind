package me.mika.midomikalifebind.Commands;

import me.mika.midomikalifebind.MidoMika_LifeBind;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Set;

public class UnbindCommand extends LifeBindSubCommands{
    @Override
    public String getName() {
        return "unbind";

    }

    @Override
    public String getDescription() {
        return "unbind life to another player";

    }

    @Override
    public String getSyntax() {
        return "/lb unbind";

    }

    @Override
    public void perform(Player player, String[] args) {

        // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
        NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
        PersistentDataContainer data = player.getPersistentDataContainer();

        String splitData = data.get(key, PersistentDataType.STRING);
        if (splitData != null) {
            String[] splitedData = splitData.split("-");

            outerForLoop:
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (player.getName().equals(splitedData[0])) {
                    if (Bukkit.getPlayer(splitedData[1]) != null) {
                        Bukkit.getPlayer(splitedData[1]).sendMessage(ChatColor.GOLD + splitedData[0] + ChatColor.YELLOW + " unbind life with you");
                        PersistentDataContainer player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();
                        player2Data.remove(key);

                    }
                    PersistentDataContainer player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                    player1Data.remove(key);
                    player.sendMessage(ChatColor.GREEN + "Success unbind life with " + ChatColor.GOLD + splitedData[1]);
                    break outerForLoop;

                }else if (player.getName().equals(splitedData[1])){
                    if (Bukkit.getPlayer(splitedData[0]) != null) {
                        Bukkit.getPlayer(splitedData[0]).sendMessage(ChatColor.GOLD + splitedData[0] + ChatColor.YELLOW + " unbind life with you");
                        PersistentDataContainer player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                        player1Data.remove(key);

                    }
                    PersistentDataContainer player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();
                    player2Data.remove(key);
                    player.sendMessage(ChatColor.GREEN + "Success unbind life with " + ChatColor.GOLD + splitedData[0]);
                    break outerForLoop;

                }
            }
        }else {
            player.sendMessage(ChatColor.RED + "You are not life bind with any players");

        }



    }
}
