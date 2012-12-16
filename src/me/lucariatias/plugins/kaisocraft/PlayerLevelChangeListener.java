package me.lucariatias.plugins.kaisocraft;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerLevelChangeListener implements Listener {
	
	@EventHandler
	public void onPlayerLevelChangeEvent(PlayerLevelChangeEvent event) {
		if (event.getNewLevel() > event.getOldLevel()) {
			event.getPlayer().sendMessage(ChatColor.YELLOW + "You levelled up!");
			event.getPlayer().sendMessage(ChatColor.GREEN + "Health and food restored.");
			event.getPlayer().sendMessage(ChatColor.AQUA + "Level: " + event.getOldLevel() + " --> " + event.getNewLevel());
			event.getPlayer().sendMessage(ChatColor.AQUA + "HP: " + event.getPlayer().getHealth() + "/" + event.getPlayer().getMaxHealth() + " --> " + event.getPlayer().getMaxHealth() + "/" + event.getPlayer().getMaxHealth());
			event.getPlayer().sendMessage(ChatColor.AQUA + "Attack: " + ((int) (Math.ceil((event.getOldLevel()) / 2)) + 5) + " --> " + ((int) (Math.ceil((event.getNewLevel()) / 2)) + 5));
			event.getPlayer().sendMessage(ChatColor.AQUA + "Defence: " + KaisoCraft.getPlayerDefence(event.getPlayer(), event.getOldLevel()) + " --> " + KaisoCraft.getPlayerDefence(event.getPlayer(), event.getNewLevel()));
			event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
			event.getPlayer().setFoodLevel(20);
			event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 0);
			event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0));
		}
	}

}
