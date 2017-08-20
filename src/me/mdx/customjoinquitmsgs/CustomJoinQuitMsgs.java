package me.mdx.customjoinquitmsgs;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomJoinQuitMsgs extends JavaPlugin implements Listener {
	
	FileConfiguration config;
	File cFile;
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		cFile = new File(getDataFolder(), "config.yml:");
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("join-msg").replace("%player%", e.getPlayer().getName())));
	}
	
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("quit-msg").replace("%player%", e.getPlayer().getName())));
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("cm")) {
			if (!sender.hasPermission("cm.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!)" + ChatColor.RED + " No Permission");
				return true;
			}
			sender.sendMessage("   ");
			sender.sendMessage(ChatColor.DARK_GREEN + "---------- CustomJoinQuitMessages ----------");
			sender.sendMessage(ChatColor.GREEN + "Created By " + ChatColor.BOLD + "MegaDestroeyrX");
			sender.sendMessage(ChatColor.GREEN + "Version " + ChatColor.BOLD + "1.0");
			sender.sendMessage(ChatColor.DARK_GREEN + "--------------------------------------------");
			sender.sendMessage("   ");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("cmreload")) {
			if (!sender.hasPermission("cm.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!)" + ChatColor.RED + " No Permission");
				return true;
			}
			config = YamlConfiguration.loadConfiguration(cFile);
			sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "(!)" + ChatColor.GREEN + " Config Reloaded!");
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("cmsetjoin")) {
			if (!sender.hasPermission("cm.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!)" + ChatColor.RED + " No Permission");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!)" + ChatColor.RED + " Please specify a message!");
				return true;
			}
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				str.append(args[i] + " ");
			}
			String join = str.toString();
			getConfig().set("join-msg", join);
			saveConfig();
			sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "(!)" + ChatColor.GREEN + " Join message has been set to: " + ChatColor.translateAlternateColorCodes('&', join));
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("cmsetquit")) {
			if (!sender.hasPermission("cm.admin")) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!)" + ChatColor.RED + " No Permission");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "(!)" + ChatColor.RED + " Please specify a message!");
				return true;
			}
			StringBuilder str = new StringBuilder();
			for (int i = 0; i < args.length; i++) {
				str.append(args[i] + " ");
			}
			String quit = str.toString();
			getConfig().set("quit-msg", quit);
			saveConfig();
			sender.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "(!)" + ChatColor.GREEN + " Quit message has been set to: " + ChatColor.translateAlternateColorCodes('&', quit));
			return true;
		}
		return true;
	}
}
