package me.mika.midomikalifebind.Commands;

import me.mika.midomikalifebind.MidoMika_LifeBind;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class AcceptCommand extends LifeBindSubCommands{
    @Override
    public String getName() {
        return "accept";

    }

    @Override
    public String getDescription() {
        return "accept life bind with another player";

    }

    @Override
    public String getSyntax() {
        return "/lb accept";

    }

    @Override
    public void perform(Player player, String[] args) {
        for (Player key : BindCommand.lifeBind.keySet()){
            BindCommand.lifeBind.clear();
            BindCommand.lifeBind.put(player, key);

        }

        if (BindCommand.lifeBind.get(player) != null) {
            // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
            NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
            PersistentDataContainer player1Data = BindCommand.lifeBind.get(player).getPersistentDataContainer();
            PersistentDataContainer player2Data = player.getPersistentDataContainer();


            if (!player1Data.has(key, PersistentDataType.STRING)) {
                player1Data.set(key, PersistentDataType.STRING, BindCommand.lifeBind.get(player).getName() + "-" + player.getName());
                player2Data.set(key, PersistentDataType.STRING, BindCommand.lifeBind.get(player).getName() + "-" + player.getName());

                player.sendMessage(ChatColor.GREEN + "Success life bind with " + ChatColor.GOLD + BindCommand.lifeBind.get(player).getName());
                Bukkit.getPlayer(BindCommand.lifeBind.get(player).getName()).sendMessage(ChatColor.GOLD + player.getName() + ChatColor.GREEN + " accepted your request");

            } else {
                player.sendMessage(ChatColor.RED + "You already life bind with someone");

            }
        }

        BindCommand.lifeBind.clear();

    }
}
