package io.papermc.testplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ItemRun extends JavaPlugin implements Listener {
    final ArrayList<String> validItems = new ArrayList<>(List.of(
            // Natural Blocks
            "grass_block", "dirt", "stone", "cobblestone", "oak_log",
            "birch_log", "spruce_log", "sand", "gravel", "clay",

            // Ores & Minerals
            "coal", "raw_iron", "raw_copper", "raw_gold", "diamond",
            "emerald", "redstone", "lapis_lazuli", "amethyst_shard", "flint",

            // Tools & Gear
            "iron_pickaxe", "diamond_sword", "iron_axe", "iron_shovel", "wooden_hoe",
            "bucket", "spyglass", "clock", "compass", "shears",

            // Food & Crops
            "apple", "wheat_seeds", "wheat", "carrot", "potato",
            "melon_slice", "sweet_berries", "raw_beef", "raw_porkchop", "raw_chicken",

            // Mob Drops & Materials
            "string", "spider_eye", "rotten_flesh", "bone", "gunpowder",
            "leather", "feathers", "ink_sac", "slimeball", "sugar_cane"));

    StartItemRun startGame = new StartItemRun();

    private String getNextItem() {
        Random random = new Random();
        int randomIndex = random.nextInt(validItems.size());
        return validItems.get(randomIndex);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
        startGame.giveBlindness(event.getPlayer(), 10);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        String randomItem = this.getNextItem();
        event.getPlayer().sendMessage(Component.text("You must find " + randomItem + "!"));
    }
}
