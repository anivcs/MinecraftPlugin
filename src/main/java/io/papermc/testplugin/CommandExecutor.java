package io.papermc.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        getLogger().info("Command sent: " + command.getName());
        getLogger().info("Args sent: " + (args == null ? "none" : args[0]));
        if(command.getName().equalsIgnoreCase("itemrun")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("start")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Success!");
                    StartItemRun startItemRun = new StartItemRun();
                    startItemRun.giveBlindness(player, 10);
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                return true;
            }
        }
        return false;
    }
}
