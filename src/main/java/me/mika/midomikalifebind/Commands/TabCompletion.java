package me.mika.midomikalifebind.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TabCompletion implements TabCompleter {
    BindCommand bindCommand = new BindCommand();
    UnbindCommand unbindCommand = new UnbindCommand();
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1){
            List<String> arguments = new ArrayList<>();
            arguments.add(bindCommand.getName());
            arguments.add(unbindCommand.getName());

            return arguments;

        }
        return null;
    }
}
