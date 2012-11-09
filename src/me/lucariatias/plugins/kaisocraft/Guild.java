package me.lucariatias.plugins.kaisocraft;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Guild {
	
	private String name;
	private String description;
	private String leader;
	private List<String> players = new ArrayList<String>();
	
	public Guild(String name, String leader) {
		this(name, "A KaisoCraft Guild", leader);
	}
	
	public Guild(String name, String description, String leader) {
		this.name = name;
		this.description = description;
		this.leader = leader;
		this.addPlayer(leader);
		KaisoCraft.addGuild(this);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		SaveDataManager guildDataManager = new SaveDataManager();
		try {
			guildDataManager.removeGuild(this);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		KaisoCraft.removeGuild(this);
		this.name = name;
		KaisoCraft.addGuild(this);
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the leader
	 */
	public String getLeader() {
		return this.leader;
	}
	
	/**
	 * @param leader the leader to set
	 */
	public void setLeader(String leader) {
		if (this.players.contains(leader)) {
			this.leader = leader;
		} else {
			this.addPlayer(leader);
			this.leader = leader;
		}
	}

	/**
	 * @param player the player to add
	 */
	public void addPlayer(String player) {
		
		if (!this.players.contains(player)) {
			if (KaisoCraft.getPlayerGuild(player) == null) {
				this.players.add(player);
				KaisoCraft.setPlayerGuild(player, this);
			}
		}
	}
	
	/**
	 * @param player the player to remove
	 */
	public void removePlayer(String player) {
		this.players.remove(player);
		KaisoCraft.removePlayerGuild(player);
		if (this.getLeader() == player) {
			this.disband();
		}
	}
	
	/**
	 * @return the players in the guild
	 */
	public List<String> getPlayers() {
		return this.players;
	}
	
	public void disband() {
		for (String player : this.getPlayers()) {
			this.removePlayer(player);
		}
		this.setLeader(null);
		KaisoCraft.removeGuild(this);
		SaveDataManager guildDataManager = new SaveDataManager();
		try {
			guildDataManager.removeGuild(this);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

}
