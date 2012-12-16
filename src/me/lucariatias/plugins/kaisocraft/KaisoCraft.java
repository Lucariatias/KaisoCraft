package me.lucariatias.plugins.kaisocraft;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class KaisoCraft extends JavaPlugin {
	
	private static Map<String, Guild> guilds = new HashMap<String, Guild>();
	private static Map<String, Guild> playerGuilds = new HashMap<String, Guild>();
	private SaveDataManager guildDataManager = new SaveDataManager();
	
	public void onEnable() {
		this.getCommand("guild").setExecutor(new GuildCommand());
		this.getCommand("party").setExecutor(new PartyCommand());
		guildDataManager.loadGuilds();
		this.getServer().getPluginManager().registerEvents(new EnchantItemListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerLevelChangeListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		this.getServer().getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
	}
	
	public void onDisable() {
		try {
			guildDataManager.saveGuilds();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	//////////////////////////////////////////////////
	//                 GUILD METHODS                //
	//////////////////////////////////////////////////
	
	/**
	 * @param name the exact name of the guild to get
	 * @return the guild
	 */
	public static Guild getGuild(final String name) {
		return guilds.get(name);
	}
	
	/**
	 * @param player the player to get the guild of
	 * @return the guild the player is a memeber of
	 */
	public static Guild getPlayerGuild(final String player) {
		for (Guild guild : getGuilds()) {
			if (guild.getPlayers().contains(player)) {
				return guild;
			}
		}
		return null;
	}
	
	/**
	 * @param guild the guild to add
	 */
	protected static void addGuild(Guild guild) {
		guilds.put(guild.getName(), guild);
	}
	
	protected static void removeGuild(Guild guild) {
		guilds.remove(guild.getName());
	}
	
	/**
	 * @param name the name of the guild to create
	 * @param leader the leader of the guild to create
	 * @return the guild
	 */
	public static Guild createGuild(String name, String leader) {
		Guild guild = new Guild(name, leader);
		return guild;
	}
	
	/**
	 * @return a collection containing all guilds
	 */
	public static Collection<Guild> getGuilds() {
		return guilds.values();
	}
	
	/**
	 * @param player the player to add to a guild
	 * @param guild the guild to add the player to
	 */
	protected static void setPlayerGuild(String player, Guild guild) {
		playerGuilds.put(player, guild);
	}
	
	/**
	 * @param player the player to remove guild
	 */
	protected static void removePlayerGuild(String player) {
		playerGuilds.remove(player);
	}
	
	//////////////////////////////////////////////////
	//                 PARTY METHODS                //
	//////////////////////////////////////////////////
	
	protected static Map<String, Inventory> trades = new HashMap<String, Inventory>();
	
	/**
	 * @param entity the entity to get the level of
	 * @return the level of the entity
	 */
	public static Integer getEntityLevel(Entity entity) {
		if (entity instanceof Player) {
			return ((Player) entity).getLevel();
		} else {
			if (entity.getWorld().getEnvironment() == Environment.NORMAL) {
				return (int) (Math.ceil(entity.getLocation().distance(entity.getWorld().getSpawnLocation()) / 32));
			} else if (entity.getWorld().getEnvironment() == Environment.NETHER) {
				return (int) (Math.ceil(entity.getLocation().distance(entity.getWorld().getSpawnLocation()) / 32)) + 25;
			} else if (entity.getWorld().getEnvironment() == Environment.THE_END) {
				return (int) (Math.ceil(entity.getLocation().distance(entity.getWorld().getSpawnLocation()) / 32)) + 50;
			}
		}
		return (int) (Math.ceil(entity.getLocation().distance(entity.getWorld().getSpawnLocation()) / 32));
	}
	
	/**
	 * @param player the player to get the exp of
	 * @return the exp of the player
	 */
	public static Integer getExp(Player player) {
		return (int) (Math.round(player.getExp() * player.getExpToLevel()));
	}
	
	/**
	 * @param player the player to get the attack of
	 * @param level the level to get the attack at
	 * @return the attack of the player at the given level
	 */
	public static Integer getPlayerAttack(Player player, Integer level) {
		return (int) (Math.ceil(level / 2) + 5);
	}
	
	/**
	 * @param player the player to get the attack of
	 * @return the attack of the player
	 */
	public static Integer getPlayerAttack(Player player) {
		return KaisoCraft.getPlayerAttack(player, player.getLevel());
	}
	
	/**
	 * @param player the player to get the defence of
	 * @param level the level to get the defence at
	 * @return the defence of the player
	 */
	public static Integer getPlayerDefence(Player player, Integer level) {
		Integer defence = 0;
		
		if (player.getInventory().getHelmet() != null) {
			if (player.getInventory().getHelmet().getType() != null) {
				if (player.getInventory().getHelmet().getType() == Material.LEATHER_HELMET) {
					defence += 1;
				}
				
				if (player.getInventory().getHelmet().getType() == Material.GOLD_HELMET) {
					defence += 2;
				}
				
				if (player.getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET) {
					defence += 2;
				}
				
				if (player.getInventory().getHelmet().getType() == Material.IRON_HELMET) {
					defence += 2;
				}
				
				if (player.getInventory().getHelmet().getType() == Material.DIAMOND_HELMET) {
					defence += 3;
				}
			}
		}
		
		if (player.getInventory().getChestplate() != null) {
			if (player.getInventory().getChestplate().getType() != null) {
				if (player.getInventory().getChestplate().getType() == Material.LEATHER_CHESTPLATE) {
					defence += 3;
				}
				
				if (player.getInventory().getChestplate().getType() == Material.GOLD_CHESTPLATE) {
					defence += 5;
				}
				
				if (player.getInventory().getChestplate().getType() == Material.CHAINMAIL_CHESTPLATE) {
					defence += 5;
				}
				
				if (player.getInventory().getChestplate().getType() == Material.IRON_CHESTPLATE) {
					defence += 6;
				}
				
				if (player.getInventory().getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
					defence += 8;
				}
			}
		}
		
		if (player.getInventory().getLeggings() != null) {
			if (player.getInventory().getLeggings().getType() != null) {
				if (player.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS) {
					defence += 2;
				}
				
				if (player.getInventory().getLeggings().getType() == Material.GOLD_LEGGINGS) {
					defence += 3;
				}
				
				if (player.getInventory().getLeggings().getType() == Material.CHAINMAIL_LEGGINGS) {
					defence += 4;
				}
				
				if (player.getInventory().getLeggings().getType() == Material.IRON_LEGGINGS) {
					defence += 5;
				}
				
				if (player.getInventory().getLeggings().getType() == Material.DIAMOND_LEGGINGS) {
					defence += 6;
				}
			}
		}
		
		if (player.getInventory().getBoots()!= null) {
			if (player.getInventory().getBoots().getType() != null) {
				if (player.getInventory().getBoots().getType() == Material.LEATHER_BOOTS) {
					defence += 1;
				}
				
				if (player.getInventory().getBoots().getType() == Material.GOLD_BOOTS) {
					defence += 1;
				}
				
				if (player.getInventory().getBoots().getType() == Material.CHAINMAIL_BOOTS) {
					defence += 1;
				}
				
				if (player.getInventory().getBoots().getType() == Material.IRON_BOOTS) {
					defence += 2;
				}
				
				if (player.getInventory().getBoots().getType() == Material.DIAMOND_BOOTS) {
					defence += 3;
				}
			}
		}
		
		defence = (int) (Math.round(defence * (level / 2)));
		return defence;
	}
	
	/**
	 * @param player the player to get the defence of
	 * @return the player's defence
	 */
	public static Integer getPlayerDefence(Player player) {
		return KaisoCraft.getPlayerDefence(player, player.getLevel());
	}
	
	//////////////////////////////////////////////////
	//                 TEMPBAN METHODS              //
	//////////////////////////////////////////////////
	
	private static Map<String, Long> banTimes = new HashMap<String, Long>();
	
	/**
	 * @param player
	 * @return whether the player is banned
	 */
	public static Boolean isBanned(String player) {
		if (banTimes.get(player) != null) {
			return (banTimes.get(player) + 300000) - System.currentTimeMillis() >= 0;
		}
		return false;
	}
	
	/**
	 * @param player the player to tempban
	 * @param message the message to send to the player upon being banned
	 */
	public static void ban(String player, String message) {
		banTimes.put(player, System.currentTimeMillis());
		Bukkit.getServer().getPlayerExact(player).kickPlayer(message);
	}
	
	/**
	 * @param player the player to get the ban time remaining of
	 * @return the ban time remaining
	 */
	public static String getBanTimeRemaining(String player) {
		if (banTimes.get(player) != null) {
			return (int) (Math.floor(((((banTimes.get(player) + 300000) - System.currentTimeMillis()) / 1000) / 60))) + "m " + ((((banTimes.get(player) + 300000) - System.currentTimeMillis()) / 1000) % 60) + "s";
		}
		return "0m 0s";
	}

}
