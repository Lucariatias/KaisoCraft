package me.lucariatias.plugins.kaisocraft;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AsyncPlayerChatListener implements Listener {
	
	@EventHandler
	public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
		SpellType type = null;
		Integer level = null;
		
		if (event.getMessage().startsWith("ignis")) {
			type = SpellType.FIRE;
		}
		
		if (event.getMessage().startsWith("glacies")) {
			type = SpellType.ICE;
		}
		
		if (event.getMessage().startsWith("ventus")) {
			type = SpellType.WIND;
		}
		
		if (event.getMessage().startsWith("terra")) {
			type = SpellType.EARTH;
		}
		
		if (event.getMessage().startsWith("aqua")) {
			type = SpellType.WATER;
		}
		
		if (event.getMessage().endsWith("unum")) {
			level = 1;
		}
		
		if (event.getMessage().endsWith("duo")) {
			level = 2;
		}
		
		if (event.getMessage().endsWith("tres")) {
			level = 3;
		}
		
		if (event.getMessage().endsWith("quattor")) {
			level = 4;
		}
		
		if (event.getMessage().endsWith("quinque")) {
			level = 5;
		}
		
		if (type != null && level != null) {
			if (type == SpellType.FIRE) {
				for (Entity entity : event.getPlayer().getWorld().getEntities()) {
					if (event.getPlayer().getLocation().distance(entity.getLocation()) <= level * 4) {
						if (entity != event.getPlayer()) {
							entity.setFireTicks(level * 200);
						}
					}
				}
				
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 160, 0), true);
			}
			
			if (type == SpellType.ICE) {
				for (Entity entity : event.getPlayer().getWorld().getEntities()) {
					if (event.getPlayer().getLocation().distance(entity.getLocation()) <= level * 4) {
						if (entity instanceof LivingEntity) {
							if (entity != event.getPlayer()) {
								((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 3));
							}
						}
					}
				}
				
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 160, 0), true);
			}
			
			if (type == SpellType.EARTH) {
				Block block = event.getPlayer().getLineOfSight(null, 64).get(event.getPlayer().getLineOfSight(null, 64).size() - 1);
				for (int i = 1; i <= level; ++i) {
					block = block.getRelative(BlockFace.UP);
					block.setType(Material.DIRT);
				}
				
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 160, 0), true);
			}
			
			if (type == SpellType.WATER) {
				Block block = event.getPlayer().getLineOfSight(null, 64).get(event.getPlayer().getLineOfSight(null, 64).size() - 1);
				for (int i = 1; i <= level; ++i) {
					block = block.getRelative(BlockFace.UP);
					block.setType(Material.WATER);
				}
				
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 160, 0), true);
			}
			
			if (type == SpellType.WIND) {
				for (Entity entity : event.getPlayer().getWorld().getEntities()) {
					if (event.getPlayer().getLocation().distance(entity.getLocation()) <= level * 8) {
						Block block = entity.getLocation().getBlock();
						for (int i = 1; i <= level * 4; ++i) {
							block = block.getRelative(BlockFace.UP);
						}
						if (entity != event.getPlayer()) {
							entity.teleport(block.getLocation());
						}
					}
				}
				
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, level * 160, 0), true);
			}
		}
	}

}
