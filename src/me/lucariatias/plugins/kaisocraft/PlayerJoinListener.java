package me.lucariatias.plugins.kaisocraft;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	
	private KaisoCraft plugin;
	
	public PlayerJoinListener(KaisoCraft plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage("񍦾�2");
		event.getPlayer().sendMessage(ChatColor.GREEN + "Hello, " + event.getPlayer().getDisplayName() + ChatColor.GREEN + "!");
		event.getPlayer().sendMessage(ChatColor.GREEN + "Welcome to a " + ChatColor.DARK_AQUA + "KaisoCraft" + ChatColor.GREEN + " server!");
		event.getPlayer().sendMessage(ChatColor.GREEN + "This server is running " + ChatColor.DARK_AQUA + "KaisoCraft " + plugin.getDescription().getVersion());
	}

}
