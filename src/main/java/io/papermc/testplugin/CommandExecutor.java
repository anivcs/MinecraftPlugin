package io.papermc.testplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class CommandExecutor implements org.bukkit.command.CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        getLogger().info("Command sent: " + command.getName());
        getLogger().info("Args sent: " + (args == null ? "none" : args[0]));
        if (!command.getName().equalsIgnoreCase("itemrun")) {
            return false;
        }

        if (args.length == 0) {
            return false;
        }

        return switch (args[0].toLowerCase()) {
            case "start" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Success!");
                    StartItemRun startItemRun = new StartItemRun();
                    startItemRun.giveBlindness(player, 10);
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "pause" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Game Paused.");
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "resume" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Game Resumed");
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "quit" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Game Quit.");
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            default -> {
                sender.sendMessage("Error.");
                yield false;
            }
        };



    }


}
