package me.lucariatias.plugins.kaisocraft;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class PlayerLoginListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		if (KaisoCraft.isBanned(event.getPlayer().getName())) {
			event.disallow(Result.KICK_OTHER, ChatColor.RED + "You have to wait " + KaisoCraft.getBanTimeRemaining(event.getPlayer().getName()) + " before you can play again!");
		} else {
			event.allow();
		}
	}

}
