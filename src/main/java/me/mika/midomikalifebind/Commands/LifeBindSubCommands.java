package me.mika.midomikalifebind.Commands;

import org.bukkit.entity.Player;

public abstract class LifeBindSubCommands {
    public abstract String getName();
    public abstract String getDescription();
    public abstract String getSyntax();
    public abstract void perform(Player player, String args[]);
}
