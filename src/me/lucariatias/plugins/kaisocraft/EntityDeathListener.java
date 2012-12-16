package me.lucariatias.plugins.kaisocraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		Integer multiplier = 0;
		for (Player player : event.getEntity().getWorld().getPlayers()) {
			if (player.getLocation().distance(event.getEntity().getLocation()) <= 16) {
				multiplier += KaisoCraft.getEntityLevel(event.getEntity()) / (KaisoCraft.getEntityLevel(player) + 1);
			}
		}
		event.setDroppedExp(event.getDroppedExp() * (multiplier + 1));
	}

}
