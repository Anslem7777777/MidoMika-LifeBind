package me.mika.midomikalifebind.Listeners;

import me.mika.midomikalifebind.MidoMika_LifeBind;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlayerJoinListener implements Listener {
    private PersistentDataContainer player1Data;
    private PersistentDataContainer player2Data;
    private Set<String> onlinePlayerList = new HashSet<>();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        String splitData = playerData.get(key, PersistentDataType.STRING);
        if (splitData != null) {
            String[] splitedData = splitData.split("-");
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                onlinePlayerList.add(onlinePlayer.getName());

            }

            if (onlinePlayerList.contains(splitedData[0]) && onlinePlayerList.contains(splitedData[1])){
                player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();

                if (Objects.equals(player1Data.get(key, PersistentDataType.STRING), player2Data.get(key, PersistentDataType.STRING))){
                    if (p.getName().equals(splitedData[0])) {
                        for (PotionEffect effect : p.getActivePotionEffects()) {
                            p.removePotionEffect(effect.getType());
                        }
                        Bukkit.getPlayer(splitedData[0]).addPotionEffects(Bukkit.getPlayer(splitedData[1]).getActivePotionEffects());

                    }else if (p.getName().equals(splitedData[1])){
                        for (PotionEffect effect : p.getActivePotionEffects()) {
                            p.removePotionEffect(effect.getType());
                        }
                        Bukkit.getPlayer(splitedData[1]).addPotionEffects(Bukkit.getPlayer(splitedData[0]).getActivePotionEffects());

                    }


                }else {
                    if (p.getName().equals(splitedData[0])) {
                        player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                        player1Data.remove(key);
                        p.sendMessage( ChatColor.GOLD + splitedData[1] + ChatColor.YELLOW + " unbind life with you");

                    }else if (p.getName().equals(splitedData[1])){
                        player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();
                        player2Data.remove(key);
                        p.sendMessage(ChatColor.GOLD + splitedData[0] + ChatColor.YELLOW + " unbind life with you");

                    }
                }


            }else {


            }

        }

        if (onlinePlayerList != null) {
            onlinePlayerList.clear();
        }
    }
}
