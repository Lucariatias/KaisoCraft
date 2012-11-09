package me.lucariatias.plugins.kaisocraft;

import java.io.IOException;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class SaveDataManager {
	
	private FileConfiguration config;
	
	protected void saveGuilds() throws IOException {
		config = Bukkit.getServer().getPluginManager().getPlugin("KaisoCraft").getConfig();
		Iterator<Guild> guildIterator = KaisoCraft.getGuilds().iterator();
		while (guildIterator.hasNext()) {
			Guild guild = guildIterator.next();
			config.set("guilds." + guild.getName() + ".description", guild.getDescription());
			config.set("guilds." + guild.getName() + ".leader", guild.getLeader());
			config.set("guilds." + guild.getName() + ".players", guild.getPlayers());
			config.save(Bukkit.getServer().getPluginManager().getPlugin("KaisoCraft").getDataFolder() + System.getProperty("file.separator") + "config.yml");
		}
	}
	
	protected void loadGuilds() {
		config = Bukkit.getServer().getPluginManager().getPlugin("KaisoCraft").getConfig();
		if (config.getConfigurationSection("guilds") != null) {
			for (String section : config.getConfigurationSection("guilds").getKeys(false)) {
				Guild guild = KaisoCraft.createGuild(section, config.getString("guilds." + section + ".leader"));
				guild.setDescription(config.getString("guilds." + section + ".description"));
				for (String player : config.getStringList("guilds." + section + ".players")) {
					guild.addPlayer(player);
				}
			}
		}
	}
	
	protected void removeGuild(Guild guild) throws IOException {
		config = Bukkit.getServer().getPluginManager().getPlugin("KaisoCraft").getConfig();
		if (config.getConfigurationSection("guilds." + guild.getName()) != null) {
			config.set("guilds." + guild.getName(), null);
			config.save(Bukkit.getServer().getPluginManager().getPlugin("KaisoCraft").getDataFolder() + System.getProperty("file.separator") + "config.yml");
		}
	}

}
