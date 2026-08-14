package io.papermc.testplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class FreezeListener implements Listener {
    private final Map<UUID, Long> frozenPlayers = new HashMap<>();




    public void freezePlayer(Player player, int seconds) {
        long unfreezeTime = System.currentTimeMillis() + (seconds * 1000L);
        frozenPlayers.put(player.getUniqueId(), unfreezeTime);
        getLogger().info("Frozen Player: " + player.getName() + ", " + player.getUniqueId());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        if (frozenPlayers.containsKey(uuid)) {

            long endTime = frozenPlayers.get(uuid);
            if (System.currentTimeMillis() < endTime) {
                Location from = event.getFrom();
                Location to = event.getTo();
                if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) {
                    event.setTo(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ(), to.getYaw(), to.getPitch()));
                }
            } else {
                frozenPlayers.remove(uuid);
            }
        }
    }
}
