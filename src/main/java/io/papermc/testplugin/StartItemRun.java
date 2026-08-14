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
    FreezeListener freezer;
    ItemGenerator itemGenerator;


    public StartItemRun(Player runner, Player hunter, FreezeListener freezer) {
        this.hunter = hunter;
        this.runner = runner;
        this.freezer = freezer;
        this.itemGenerator = new ItemGenerator();
    }

    private void giveBlindness(LivingEntity player, int seconds) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, seconds * 20, 1));
    }

    private void runnerHeadstart(int seconds) {
        this.giveBlindness(hunter, seconds);
        freezer.freezePlayer(hunter, seconds);

    }

    private void setTargetItem() {
        String targetItem = itemGenerator.generateNextItem();
        runner.sendMessage(Component.text(ChatColor.RED + "You must find " + targetItem + "!"));
    }

    public void run() {
        this.setTargetItem();
        this.runnerHeadstart(10);


    }
}
