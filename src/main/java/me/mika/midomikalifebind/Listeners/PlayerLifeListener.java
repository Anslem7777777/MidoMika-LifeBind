package me.mika.midomikalifebind.Listeners;

import me.mika.midomikalifebind.MidoMika_LifeBind;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class PlayerLifeListener implements Listener {

    private PersistentDataContainer player1Data;
    private PersistentDataContainer player2Data;
    private Set<String> onlinePlayerList = new HashSet<>();
    private static Double yHealth;
    private static Double pHealth;

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
            NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
            PersistentDataContainer playerData = p.getPersistentDataContainer();
            String splitData = playerData.get(key, PersistentDataType.STRING);
            if (splitData != null) {
                String[] splitedData = splitData.split("-");
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                    onlinePlayerList.add(onlinePlayer.getName());

                }

                if (onlinePlayerList.contains(splitedData[0]) && onlinePlayerList.contains(splitedData[1])) {
                    player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                    player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();

                    if (p.getName().equals(splitedData[0])){
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (p.getHealth() <= 0) {
                                    pHealth = 0.0;

                                } else {
                                    pHealth = p.getHealth();

                                }

                                if (p.getAbsorptionAmount() <= 0) {
                                    yHealth = 0.0;

                                } else {
                                    yHealth = p.getAbsorptionAmount();

                                }
                                // 玩家受到伤害时的处理逻辑
                                // 在这里添加处理逻辑，比如发送消息给玩家
                                if (!Bukkit.getPlayer(splitedData[1]).isDead() && !Bukkit.getPlayer(splitedData[1]).getGameMode().toString().equals("CREATIVE")) {
                                    if (yHealth != 0.0) {
                                        Bukkit.getPlayer(splitedData[1]).setAbsorptionAmount(yHealth);

                                    }else {
                                        if (pHealth != 0.0) {
                                            Bukkit.getPlayer(splitedData[1]).setAbsorptionAmount(0);
                                            Bukkit.getPlayer(splitedData[1]).setHealth(pHealth);

                                        } else {
                                            Bukkit.getPlayer(splitedData[1]).setHealth(0);

                                        }
                                    }
                                }
                            }
                        }.runTaskLater(MidoMika_LifeBind.getPlugin(), 2);

                    }else if (p.getName().equals(splitedData[1])){
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (p.getHealth() <= 0) {
                                    pHealth = 0.0;

                                } else {
                                    pHealth = p.getHealth();

                                }

                                if (p.getAbsorptionAmount() <= 0) {
                                    yHealth = 0.0;

                                } else {
                                    yHealth = p.getAbsorptionAmount();

                                }
                                // 玩家受到伤害时的处理逻辑
                                // 在这里添加处理逻辑，比如发送消息给玩家
                                if (!Bukkit.getPlayer(splitedData[0]).isDead() && !Bukkit.getPlayer(splitedData[1]).getGameMode().toString().equals("CREATIVE")) {
                                    if (yHealth != 0.0){
                                        Bukkit.getPlayer(splitedData[0]).setAbsorptionAmount(yHealth);

                                    }else {
                                        if (pHealth != 0.0) {
                                            Bukkit.getPlayer(splitedData[0]).setAbsorptionAmount(0);
                                            Bukkit.getPlayer(splitedData[0]).setHealth(pHealth);

                                        } else {
                                            Bukkit.getPlayer(splitedData[0]).setHealth(0);

                                        }
                                    }
                                }
                            }
                        }.runTaskLater(MidoMika_LifeBind.getPlugin(), 2);
                    }
                }
            }
        }
        onlinePlayerList.clear();
    }

    @EventHandler
    public void onEntityHealth(EntityRegainHealthEvent e){
        if (e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            double pHealth = p.getHealth();
            // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
            NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
            PersistentDataContainer playerData = p.getPersistentDataContainer();
            String splitData = playerData.get(key, PersistentDataType.STRING);
            if (splitData != null) {
                String[] splitedData = splitData.split("-");
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayerList.add(onlinePlayer.getName());

                }
                if (onlinePlayerList.contains(splitedData[0]) && onlinePlayerList.contains(splitedData[1])) {
                    player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                    player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();

                    if (p.getName().equals(splitedData[0])) {
                        if (!Bukkit.getPlayer(splitedData[1]).isDead() && !Bukkit.getPlayer(splitedData[1]).getGameMode().toString().equals("CREATIVE")) {
                            if ((pHealth + 1) <= p.getMaxHealth()) {
                                Bukkit.getPlayer(splitedData[1]).setHealth(pHealth + e.getAmount());

                            } else {
                                Bukkit.getPlayer(splitedData[1]).setHealth(20);

                            }
                        }
                    } else if (p.getName().equals(splitedData[1])) {
                        if (!Bukkit.getPlayer(splitedData[0]).isDead() && !Bukkit.getPlayer(splitedData[0]).getGameMode().toString().equals("CREATIVE")) {
                            if ((pHealth + 1)  <= p.getMaxHealth()) {
                                Bukkit.getPlayer(splitedData[0]).setHealth(pHealth + e.getAmount());

                            } else {
                                Bukkit.getPlayer(splitedData[0]).setHealth(20);

                            }
                        }

                    }
                }
            }
        }
        onlinePlayerList.clear();
    }

    @EventHandler
    public void playerConsumeGoldenApple(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
        NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
        PersistentDataContainer playerData = p.getPersistentDataContainer();
        String splitData = playerData.get(key, PersistentDataType.STRING);
        if (splitData != null) {
            String[] splitedData = splitData.split("-");
            player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
            player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                onlinePlayerList.add(onlinePlayer.getName());

            }
            // 检查是否玩家吃的是金苹果
            if (e.getItem().getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                if (onlinePlayerList.contains(splitedData[0]) && onlinePlayerList.contains(splitedData[1])) {
                    if (p.getName().equals(splitedData[0])) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (!Bukkit.getPlayer(splitedData[1]).isDead() && !Bukkit.getPlayer(splitedData[1]).getGameMode().toString().equals("CREATIVE")) {
                                    Bukkit.getPlayer(splitedData[1]).addPotionEffects(Bukkit.getPlayer(splitedData[0]).getActivePotionEffects());

                                }
                            }
                        }.runTaskLater(MidoMika_LifeBind.getPlugin(), 1);
                    } else if (p.getName().equals(splitedData[1])) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (!Bukkit.getPlayer(splitedData[0]).isDead() && !Bukkit.getPlayer(splitedData[0]).getGameMode().toString().equals("CREATIVE")) {
                                    Bukkit.getPlayer(splitedData[0]).addPotionEffects(Bukkit.getPlayer(splitedData[1]).getActivePotionEffects());

                                }
                            }
                        }.runTaskLater(MidoMika_LifeBind.getPlugin(), 1);
                    }
                }
            } else if (e.getItem().getType() == Material.GOLDEN_APPLE) {
                if (onlinePlayerList.contains(splitedData[0]) && onlinePlayerList.contains(splitedData[1])) {
                    if (p.getName().equals(splitedData[0])) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (!Bukkit.getPlayer(splitedData[1]).isDead() && !Bukkit.getPlayer(splitedData[1]).getGameMode().toString().equals("CREATIVE")) {
                                    Bukkit.getPlayer(splitedData[1]).addPotionEffects(Bukkit.getPlayer(splitedData[0]).getActivePotionEffects());

                                }
                            }
                        }.runTaskLater(MidoMika_LifeBind.getPlugin(), 1);
                    } else if (p.getName().equals(splitedData[1])) {
                        new BukkitRunnable() {
                                @Override
                                public void run() {
                                    if (!Bukkit.getPlayer(splitedData[0]).isDead() && !Bukkit.getPlayer(splitedData[0]).getGameMode().toString().equals("CREATIVE")) {
                                        Bukkit.getPlayer(splitedData[0]).addPotionEffects(Bukkit.getPlayer(splitedData[1]).getActivePotionEffects());

                                    }
                                }
                        }.runTaskLater(MidoMika_LifeBind.getPlugin(), 1);
                    }
                }
            }
        }
        onlinePlayerList.clear();
    }

    @EventHandler
    public void playerDead(PlayerDeathEvent e){
        if (e.getEntity() instanceof Player){
            Player p = e.getEntity();

            // 创建 NamespacedKey，用于标识你的插件和键名  获取 PersistentDataContainer
            NamespacedKey key = new NamespacedKey(MidoMika_LifeBind.getPlugin(), "LifeBindData");
            PersistentDataContainer playerData = p.getPersistentDataContainer();
            String splitData = playerData.get(key, PersistentDataType.STRING);
            if (splitData != null) {
                String[] splitedData = splitData.split("-");
                player1Data = Bukkit.getPlayer(splitedData[0]).getPersistentDataContainer();
                player2Data = Bukkit.getPlayer(splitedData[1]).getPersistentDataContainer();
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayerList.add(onlinePlayer.getName());

                }

                if (onlinePlayerList.contains(splitedData[0]) && onlinePlayerList.contains(splitedData[1])) {
                    if (p.getName().equals(splitedData[0])) {

                    } else if (p.getName().equals(splitedData[1])) {

                    }
                }
            }
        }

    }

//    @EventHandler
//    public void testPLayerMove(PlayerMoveEvent e){
//        Player p = e.getPlayer();
//        if (p.getFoodLevel() > 0) {
//            p.setFoodLevel(p.getFoodLevel() - 1);
//
//        }
//    }

//    @EventHandler
//    public void onEntityDamage(EntityDamageByEntityEvent event) {
//        Entity damager = event.getDamager();
//        Entity entity = event.getEntity();
//
//        if (entity instanceof Player && damager.getType().isAlive()) {
//            // 如果攻击者是活生物，取消伤害
//            event.setCancelled(true);
//        }
//    }
}
