package me.mika.midomikalifebind.Commands;

import me.mika.midomikalifebind.MidoMika_LifeBind;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class RejectCommand extends LifeBindSubCommands{
    @Override
    public String getName() {
        return "reject";

    }

    @Override
    public String getDescription() {
        return "reject life bind with another player";

    }

    @Override
    public String getSyntax() {
        return "/lb reject";

    }

    @Override
    public void perform(Player player, String[] args) {
        if (BindCommand.lifeBind.toString() != "{}") {
            for (Player key : BindCommand.lifeBind.keySet()) {
                BindCommand.lifeBind.clear();
                BindCommand.lifeBind.put(player, key);

            }
            player.sendMessage(ChatColor.YELLOW + "Rejected life bind with " + ChatColor.GOLD + BindCommand.lifeBind.get(player).getName());
            Bukkit.getPlayer(BindCommand.lifeBind.get(player).getName()).sendMessage(ChatColor.GOLD + player.getName() + ChatColor.RED + " rejected your request");


            BindCommand.lifeBind.clear();
        }
    }
}
