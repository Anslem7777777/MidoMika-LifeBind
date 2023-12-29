package me.mika.midomikalifebind;

import me.mika.midomikalifebind.Commands.LifeBindCommandsManager;
import me.mika.midomikalifebind.Commands.TabCompletion;
import me.mika.midomikalifebind.Listeners.PlayerJoinListener;
import me.mika.midomikalifebind.Listeners.PlayerLifeListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MidoMika_LifeBind extends JavaPlugin {

    private static MidoMika_LifeBind plugin;

    public static MidoMika_LifeBind getPlugin() {
        return plugin;

    }

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new PlayerLifeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("lb").setExecutor(new LifeBindCommandsManager());
        getCommand("lb").setTabCompleter(new TabCompletion());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
