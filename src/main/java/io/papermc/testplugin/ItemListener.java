package io.papermc.testplugin;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Bukkit.getServer;


public class ItemListener implements Listener {

    private Material targetItem = null;
    private Player targetPlayer = null;
    private ItemRun plugin;

    private void itemFound() {
        if (targetPlayer != null) {
            targetPlayer.sendMessage(Component.text("Item found!"));
            this.targetItem = null;
            this.targetPlayer = null;
        }
    }

    public ItemListener(ItemRun plugin) {
        this.plugin = plugin;
    }

    public void setPlayer(Player player) {
        this.targetPlayer = player;
    }

    public void setTargetItem(String item) {
        this.targetItem = Material.matchMaterial(item);

    }


    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        if (targetPlayer == null || targetItem == null) {
            return;
        }

        if (!(event.getItem().getItemStack().getType() == targetItem)) {
            return;
        }

        LivingEntity picker = event.getEntity();
        if (!(picker instanceof Player player && player.getUniqueId() == targetPlayer.getUniqueId())) {
            return;
        }
        this.itemFound();

    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
        if (targetPlayer == null || targetItem == null) return;

        if (!(event.getWhoClicked() instanceof Player player) || !player.getUniqueId().equals(targetPlayer.getUniqueId())) {
            return;
        }


        if (event.getRecipe().getResult().isSimilar(ItemStack.of(targetItem))) {
           this.itemFound();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (targetPlayer == null || targetItem == null) return;

        if (!(event.getWhoClicked() instanceof Player player) || !player.getUniqueId().equals(targetPlayer.getUniqueId())) {
            return;
        }

        // Schedule a 1-tick delay so Bukkit finishes moving the item into the inventory first
        getServer().getScheduler().runTask(plugin, () -> {
            if (targetPlayer != null && targetItem != null && player.getInventory().containsAtLeast(ItemStack.of(targetItem), 1)) {
                this.itemFound();
            }
        });
    }

}
