package me.lucariatias.plugins.kaisocraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!event.isCancelled()) {
			if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
				Player player = (Player) event.getDamager();
				LivingEntity entity = (LivingEntity) event.getEntity();
				player.sendMessage(ChatColor.AQUA + "==" + ChatColor.DARK_AQUA + "Lvl" + KaisoCraft.getEntityLevel(entity) + " " + event.getEntityType().toString() + ChatColor.DARK_AQUA + "==");
				player.sendMessage(ChatColor.AQUA + "Damage dealt: " + event.getDamage());
				if (entity.getHealth() - event.getDamage() > 0) {
					player.sendMessage(ChatColor.AQUA + "HP Remaining: " + ChatColor.DARK_AQUA + (entity.getHealth() - event.getDamage()) + "/" + entity.getMaxHealth());
				} else {
					player.sendMessage(ChatColor.AQUA + "HP Remaining: " + ChatColor.DARK_AQUA + 0 + "/" + entity.getMaxHealth());
				}
			}
			//TODO: Projectiles
		}
	}

}
