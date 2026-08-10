package io.papermc.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getName;

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
                    player.sendMessage(ChatColor.GREEN + "Game started!");
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
                    player.sendMessage(ChatColor.GOLD + "Game Paused.");
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "resume" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.GOLD + "Game Resumed");
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "quit" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.RED + "Game ended.");
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "team_runner" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + player.getName() + " is added to Team Speedrunner.");
                } else {
                    getLogger().info("This command must be run by a player!");
                }
                yield true;
            }
            case "team_hunter" -> {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + player.getName() + " is added to Team Hunter.");
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
