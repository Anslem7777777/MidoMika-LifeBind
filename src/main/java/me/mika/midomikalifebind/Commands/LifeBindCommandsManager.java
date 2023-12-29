package me.mika.midomikalifebind.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class LifeBindCommandsManager implements CommandExecutor {
    private ArrayList<LifeBindSubCommands> subcommands = new ArrayList<>();

    public LifeBindCommandsManager(){
        subcommands.add(new BindCommand());
        subcommands.add(new AcceptCommand());
        subcommands.add(new RejectCommand());
        subcommands.add(new UnbindCommand());

    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (strings.length > 0){
                for (int i = 0; i < getSubcommands().size(); i++){
                    if (strings[0].equalsIgnoreCase(getSubcommands().get(i).getName())){
                        //perform equals Executor
                        getSubcommands().get(i).perform(p, strings);

                    }
                }
            } else if (strings.length == 0) {

                p.sendMessage( "==========================");
                p.sendMessage(ChatColor.AQUA + "        Life Bind Commands");
                p.sendMessage("==========================");
                p.sendMessage(ChatColor.GREEN + "--------------------------");
                for (int i = 0; i < getSubcommands().size(); i++){
                    p.sendMessage( ChatColor.YELLOW + getSubcommands().get(i).getSyntax() +  ": " + ChatColor.WHITE + getSubcommands().get(i).getDescription());
                }
                p.sendMessage(ChatColor.GREEN + "--------------------------");

            }
        }
        return true;
    }

    public ArrayList<LifeBindSubCommands> getSubcommands(){
        return subcommands;

    }
}
