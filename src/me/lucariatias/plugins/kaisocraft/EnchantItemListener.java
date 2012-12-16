package me.lucariatias.plugins.kaisocraft;

import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

public class EnchantItemListener implements Listener {
	
	@EventHandler
	public void onEnchantItem(EnchantItemEvent event) {
		Random random = new Random();
		if (random.nextInt(100) <= 10) {
			event.setCancelled(true);
			event.getEnchantBlock().getWorld().createExplosion(event.getEnchantBlock().getLocation(), 4F);
			event.getEnchantBlock().getWorld().strikeLightning(event.getEnchantBlock().getLocation());
		} else {
			event.setExpLevelCost(0);
		}
	}

}
