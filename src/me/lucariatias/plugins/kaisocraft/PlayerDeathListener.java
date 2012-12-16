package me.lucariatias.plugins.kaisocraft;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.getEntity().getInventory().clear();
		KaisoCraft.ban(event.getEntity().getName(), ChatColor.RED + "You died!\n" + event.getDeathMessage() + "\nYou have been banned for 5 minutes.");
	}

}
