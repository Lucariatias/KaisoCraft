package me.lucariatias.plugins.kaisocraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR) {
			if (event.getPlayer().getItemInHand().getType() == Material.GLASS_BOTTLE) {
				if (event.getPlayer().getLevel() >= 5) {
					event.getPlayer().getInventory().removeItem(new ItemStack(Material.GLASS_BOTTLE, 1));
					event.getPlayer().getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 1));
					event.getPlayer().setLevel(event.getPlayer().getLevel() - 1);
				} else {
					event.getPlayer().sendMessage(ChatColor.RED + "You have to be level 5 or higher to fill bottles with your experience!");
				}
			}
		}
	}
	
}
