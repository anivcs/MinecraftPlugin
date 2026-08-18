package io.papermc.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class PlayerScoreboard {
    public static void showScoreboard(Player p) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();

        Objective objective = board.registerNewObjective("servername", "dummy", ChatColor.YELLOW + "ItemRun");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore("=====================").setScore(7);
        objective.getScore(ChatColor.RED + "Hunters: ").setScore(6);
        objective.getScore(ChatColor.BLACK.toString()).setScore(5);
        objective.getScore(ChatColor.BLUE + "ItemRunner: ").setScore(4);
        objective.getScore("---------------------").setScore(3);
        objective.getScore(ChatColor.GREEN + "Type '/itemrun start' to ").setScore(2);
        objective.getScore(ChatColor.GREEN + "start the game!").setScore(1);
        p.setScoreboard(board);

    }
}
