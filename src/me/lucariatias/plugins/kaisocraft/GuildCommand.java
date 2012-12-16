package me.lucariatias.plugins.kaisocraft;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("guild")) {
			if (args.length > 0) {
				sender.sendMessage(ChatColor.YELLOW + "==" + ChatColor.BLUE + "/" + cmd.getName().toLowerCase() + " " + ChatColor.DARK_BLUE + args[0].toLowerCase() + ChatColor.YELLOW + "==");
				if (args[0].equalsIgnoreCase("create")) {
					if (sender.hasPermission("kaisocraft.command.guild.create")) {
						if (args.length >= 2) {
							if (KaisoCraft.getPlayerGuild(sender.getName()) == null) {
								if (KaisoCraft.getGuild(args[1]) == null) {
									KaisoCraft.createGuild(args[1], sender.getName());
									sender.sendMessage(ChatColor.GREEN + "The guild " + ChatColor.DARK_GREEN + args[1] + ChatColor.GREEN + " was successfully created!");
								} else {
									sender.sendMessage(ChatColor.RED + "The guild " + ChatColor.DARK_RED + args[1] + ChatColor.RED + " already exists!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "You are already a member of a guild!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Incorrect usage!");
							sender.sendMessage(ChatColor.GREEN + "Usage: /guild create [guildName]");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have permission!");
						sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.create");
					}
				}
				
				if (args[0].equalsIgnoreCase("addmember")) {
					if (args.length >= 3) {
						if (KaisoCraft.getGuild(args[1]) != null) {
							if (Bukkit.getServer().getPlayer(args[2]) != null) {
								if (KaisoCraft.getGuild(args[1]).getLeader().equals(sender.getName())) {
									if (sender.hasPermission("kaisocraft.command.guild.addmember.own")) {
										if (KaisoCraft.getPlayerGuild(Bukkit.getServer().getPlayer(args[2]).getName()) == null) {
											KaisoCraft.getGuild(args[1]).addPlayer(Bukkit.getServer().getPlayer(args[2]).getName());
											sender.sendMessage(ChatColor.DARK_GREEN + Bukkit.getServer().getPlayer(args[2]).getName() + ChatColor.GREEN + " was added to the guild " + ChatColor.DARK_GREEN + args[1]);
										} else {
											sender.sendMessage(ChatColor.RED + "That player already has a guild!");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have permission!");
										sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.addmember.own");
									}
								} else {
									if (sender.hasPermission("kaisocraft.command.guild.addmember.other")) {
										if (KaisoCraft.getPlayerGuild(Bukkit.getServer().getPlayer(args[2]).getName()) == null) {
											KaisoCraft.getGuild(args[1]).addPlayer(Bukkit.getServer().getPlayer(args[2]).getName());
											sender.sendMessage(ChatColor.DARK_GREEN + Bukkit.getServer().getPlayer(args[2]).getName() + ChatColor.GREEN + " was added to the guild " + ChatColor.DARK_GREEN + args[1]);
										} else {
											sender.sendMessage(ChatColor.RED + "That player already has a guild!");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have permission!");
										sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.addmember.other");
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "That player is not online!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That guild does not exist!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Incorrect usage!");
						sender.sendMessage(ChatColor.GREEN + "Usage: /guild addmember [guildName] [memberName]");
					}
				}
				
				if (args[0].equalsIgnoreCase("removemember")) {
					if (args.length >= 3) {
						if (KaisoCraft.getGuild(args[1]) != null) {
							if (Bukkit.getServer().getPlayer(args[2]) != null) {
								if (KaisoCraft.getGuild(args[1]).getLeader().equals(sender.getName())) {
									if (sender.hasPermission("kaisocraft.command.guild.removemember.own")) {
										if (KaisoCraft.getGuild(args[1]).getPlayers().contains(Bukkit.getServer().getPlayer(args[2]))) {
											KaisoCraft.getGuild(args[1]).removePlayer(Bukkit.getServer().getPlayer(args[2]).getName());
											sender.sendMessage(ChatColor.DARK_RED + Bukkit.getServer().getPlayer(args[2]).getName() + ChatColor.RED + " was removed from the guild " + ChatColor.DARK_RED + args[1]);
										} else {
											sender.sendMessage(ChatColor.DARK_RED + Bukkit.getServer().getPlayer(args[2]).getName() + ChatColor.RED + " is not a member of the guild " + ChatColor.DARK_RED + args[1]);
										}
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have permission!");
										sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.removemember.own");
									}
								} else {
									if (sender.hasPermission("kaisocraft.command.guild.removemember.other")) {
										if (KaisoCraft.getGuild(args[1]).getPlayers().contains(Bukkit.getServer().getPlayer(args[2]))) {
											KaisoCraft.getGuild(args[1]).removePlayer(Bukkit.getServer().getPlayer(args[2]).getName());
											sender.sendMessage(ChatColor.DARK_RED + Bukkit.getServer().getPlayer(args[2]).getName() + ChatColor.RED + " was removed from the guild " + ChatColor.DARK_RED + args[1]);
										} else {
											sender.sendMessage(ChatColor.DARK_RED + Bukkit.getServer().getPlayer(args[2]).getName() + ChatColor.RED + " is not a member of the guild " + ChatColor.DARK_RED + args[1]);
										}
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have permission!");
										sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.removemember.other");
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "That player is not online!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That guild does not exist!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Incorrect usage!");
						sender.sendMessage(ChatColor.GREEN + "Usage: /guild removemember [guildName] [memberName]");
					}
				}
				
				if (args[0].equalsIgnoreCase("info")) {
					if (args.length >= 2) {
						if (sender.hasPermission("kaisocraft.command.guild.info.other")) {
							if (KaisoCraft.getGuild(args[1]) != null) {
								sender.sendMessage(ChatColor.DARK_AQUA + "Name: " + ChatColor.AQUA + KaisoCraft.getGuild(args[1]).getName());
								sender.sendMessage(ChatColor.DARK_AQUA + "Description: " + ChatColor.AQUA + KaisoCraft.getGuild(args[1]).getDescription());
								if (Bukkit.getServer().getPlayerExact(KaisoCraft.getGuild(args[1]).getLeader()) != null) {
									sender.sendMessage(ChatColor.DARK_AQUA + "Leader: " + ChatColor.LIGHT_PURPLE + KaisoCraft.getGuild(args[1]).getLeader());
								} else {
									sender.sendMessage(ChatColor.DARK_AQUA + "Leader: " + ChatColor.DARK_PURPLE + KaisoCraft.getGuild(args[1]).getLeader());
								}
								sender.sendMessage(ChatColor.DARK_AQUA + "Players: ");
								for (String player : KaisoCraft.getGuild(args[1]).getPlayers()) {
									if (Bukkit.getServer().getPlayerExact(player) != null) {
										sender.sendMessage(ChatColor.LIGHT_PURPLE + player);
									} else {
										sender.sendMessage(ChatColor.DARK_PURPLE + player);
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "That guild does not exist!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission!");
							sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.info.other");
						}
					} else {
						if (sender.hasPermission("kaisocraft.command.guild.info.own")) {
							if (KaisoCraft.getPlayerGuild(sender.getName()) != null) {
								sender.sendMessage(ChatColor.DARK_AQUA + "Name: " + ChatColor.AQUA + KaisoCraft.getPlayerGuild(sender.getName()).getName());
								sender.sendMessage(ChatColor.DARK_AQUA + "Description: " + ChatColor.AQUA + KaisoCraft.getPlayerGuild(sender.getName()).getDescription());
								if (Bukkit.getServer().getPlayerExact(KaisoCraft.getPlayerGuild(sender.getName()).getLeader()) != null) {
									sender.sendMessage(ChatColor.DARK_AQUA + "Leader: " + ChatColor.LIGHT_PURPLE + KaisoCraft.getPlayerGuild(sender.getName()).getLeader());
								} else {
									sender.sendMessage(ChatColor.DARK_AQUA + "Leader: " + ChatColor.DARK_PURPLE + KaisoCraft.getPlayerGuild(sender.getName()).getLeader());
								}
								sender.sendMessage(ChatColor.DARK_AQUA + "Players: ");
								for (String player : KaisoCraft.getPlayerGuild(sender.getName()).getPlayers()) {
									if (Bukkit.getServer().getPlayerExact(player) != null) {
										sender.sendMessage(ChatColor.LIGHT_PURPLE + player);
									} else {
										sender.sendMessage(ChatColor.DARK_PURPLE + player);
									}
								}
							} else {
								sender.sendMessage(ChatColor.RED + "You are not a member of a guild!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "You do not have permission!");
							sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.info.own");
						}
					}
				}
				
				if (args[0].equalsIgnoreCase("leave")) {
					if (sender.hasPermission("kaisocraft.command.guild.leave")) {
						if (KaisoCraft.getPlayerGuild(sender.getName()) != null) {
							if (sender.getName().equals(KaisoCraft.getPlayerGuild(sender.getName()).getLeader())) {
								if (sender.hasPermission("kaisocraft.command.guild.disband")) {
									sender.sendMessage(ChatColor.RED + "The guild " + ChatColor.DARK_RED + KaisoCraft.getPlayerGuild(sender.getName()).getName() + ChatColor.RED + " was disbanded!");
									KaisoCraft.getPlayerGuild(sender.getName()).disband();
								} else {
									sender.sendMessage(ChatColor.RED + "You do not have permission!");
									sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.disband");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "You left the guild " + ChatColor.DARK_RED + KaisoCraft.getPlayerGuild(sender.getName()).getName());
								KaisoCraft.getPlayerGuild(sender.getName()).removePlayer(sender.getName());
							}
						} else {
							sender.sendMessage(ChatColor.RED + "You are not a member of a guild!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "You do not have permission to leave guilds!");
						sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.leave");
					}
				}
				
				if (args[0].equalsIgnoreCase("join")) {
					if (args.length >= 2) {
						if (KaisoCraft.getGuild(args[1]) != null) {
							if (sender.hasPermission("kaisocraft.command.guild.join")) {
								if (KaisoCraft.getPlayerGuild(sender.getName()) == null) {
									KaisoCraft.getGuild(args[1]).addPlayer(sender.getName());
									sender.sendMessage(ChatColor.GREEN + "You joined the guild " + ChatColor.DARK_GREEN + args[1]);
								} else {
									sender.sendMessage(ChatColor.RED + "You are already a member of a guild!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "You do not have permission!");
								sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.join");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "That guild does not exist!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Incorrect usage!");
						sender.sendMessage(ChatColor.GREEN + "Usage: /guild join [guildName]");
					}
				}
				
				if (args[0].equalsIgnoreCase("list")) {
					if (sender.hasPermission("kaisocraft.command.guild.list")) {
						Iterator<Guild> guildIterator = KaisoCraft.getGuilds().iterator();
						sender.sendMessage(ChatColor.DARK_AQUA + "Guilds:");
						while (guildIterator.hasNext()) {
							Guild guild = guildIterator.next();
							if (Bukkit.getServer().getPlayerExact(guild.getLeader()) != null) {
								sender.sendMessage(ChatColor.AQUA + guild.getName() + " (" + ChatColor.LIGHT_PURPLE + guild.getLeader() + ChatColor.AQUA + "'s guild)");
							} else {
								sender.sendMessage(ChatColor.AQUA + guild.getName() + " (" + ChatColor.DARK_PURPLE + guild.getLeader() + ChatColor.AQUA + "'s guild)");
							}
						}
					}
				}
				
				if (args[0].equalsIgnoreCase("modify")) {
					if (args.length >= 2) {
						if (args[1].equalsIgnoreCase("name")) {
							if (args.length >= 3) {
								if (KaisoCraft.getGuild(args[2]) != null) {
									if (sender.hasPermission("kaisocraft.command.guild.modify.other") || (KaisoCraft.getGuild(args[2]).equals(sender.getName()) && sender.hasPermission("kaisocraft.command.guild.modify.own"))) {
										if (args.length >= 4) {
											sender.sendMessage(ChatColor.GREEN + "The name of the guild " + ChatColor.DARK_GREEN + args[2] + ChatColor.GREEN + " was changed to " + ChatColor.DARK_GREEN + args[3]);
											KaisoCraft.getPlayerGuild(sender.getName()).setName(args[3]);
										} else {
											sender.sendMessage(ChatColor.RED + "Incorrect usage!");
											sender.sendMessage(ChatColor.GREEN + "Usage: /guild modify name [guildName] [name]");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have permission!");
										sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.modify.other");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "That guild does not exist!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "Incorrect usage!");
								sender.sendMessage(ChatColor.GREEN + "Usage: /guild modify name [guildName] [name]");
							}
						}
						
						if (args[1].equalsIgnoreCase("desc") || args[1].equalsIgnoreCase("description")) {
							if (args.length >= 3) {
								if (KaisoCraft.getGuild(args[2]) != null) {
									if (sender.hasPermission("kaisocraft.command.guild.modify.other") || (KaisoCraft.getGuild(args[2]).equals(sender.getName()) && sender.hasPermission("kaisocraft.command.guild.modify.own"))) {
										if (args.length >= 4) {
											String desc = "";
											for (int i = 3; i <= args.length - 1; ++i) {
												desc += args[i] + " ";
											}
											sender.sendMessage(ChatColor.GREEN + "The description of the guild " + ChatColor.DARK_GREEN + args[2] + ChatColor.GREEN + " was changed to " + ChatColor.DARK_GREEN + desc);
											KaisoCraft.getPlayerGuild(sender.getName()).setDescription(desc);
										} else {
											sender.sendMessage(ChatColor.RED + "Incorrect usage!");
											sender.sendMessage(ChatColor.GREEN + "Usage: /guild modify desc [guildName] [description]");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "You do not have permission!");
										sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.modify.other");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "That guild does not exist!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "Incorrect usage!");
								sender.sendMessage(ChatColor.GREEN + "Usage: /guild modify name [guildName] [name]");
							}
						}
						
						if (args[0].equalsIgnoreCase("locate")) {
							if (sender.hasPermission("kaisocraft.command.party.locate")) {
								if (args.length >= 2) {
									if (Bukkit.getServer().getPlayer(args[1]) != null) {
										Player player = Bukkit.getServer().getPlayer(args[1]);
										if (KaisoCraft.getPlayerGuild(player.getName()) == KaisoCraft.getPlayerGuild(sender.getName())) {
											sender.sendMessage(ChatColor.DARK_AQUA + player.getName() + ChatColor.AQUA + " is at " + ChatColor.DARK_AQUA + (int) (player.getLocation().getX()) + ", " + (int) (player.getLocation().getY()) + ", " + (int) (player.getLocation().getZ()) + ChatColor.AQUA + " in world " + ChatColor.DARK_AQUA + player.getWorld().getName());
										} else {
											sender.sendMessage(ChatColor.DARK_RED + player.getName() + ChatColor.RED + "is not a member of your guild!");
										}
									} else {
										sender.sendMessage(ChatColor.RED + "That player is not currently online!");
									}
								} else {
									sender.sendMessage(ChatColor.RED + "Incorrect usage!");
									sender.sendMessage(ChatColor.GREEN + "Usage: /locate [player]");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "You do not have permission!");
								sender.sendMessage(ChatColor.GREEN + "Permission node: " + ChatColor.DARK_GREEN + "kaisocraft.command.guild.modify.other");
							}
						}
					}
				}
			}
			return true;
		}
		return false;
	}

}
