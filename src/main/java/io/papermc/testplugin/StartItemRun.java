package io.papermc.testplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class StartItemRun {
    Player runner;
    Player hunter;
    ItemRun plugin;


    public StartItemRun(Player runner, Player hunter, ItemRun plugin) {
        this.hunter = hunter;
        this.runner = runner;
        this.plugin = plugin;
    }

    private void giveBlindness(LivingEntity player, int seconds) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, seconds * 20, 1));
    }

    private void runnerHeadstart(int seconds) {
        this.giveBlindness(hunter, seconds);
        plugin.freezer.freezePlayer(hunter, seconds);

    }

    private void setTargetItemAndPlayer() {
        String targetItem = plugin.itemGenerator.generateNextItem();
        plugin.itemDetector.setTargetItem(targetItem);
        plugin.itemDetector.setPlayer(runner);
        runner.sendMessage(Component.text(ChatColor.RED + "You must find " + targetItem + "!"));
    }

    public void run() {
        this.setTargetItemAndPlayer();
        this.runnerHeadstart(10);


    }
}
