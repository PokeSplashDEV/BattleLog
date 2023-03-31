package com.bencrow11.battlelog.commands;

import com.bencrow11.battlelog.Utils;
import com.bencrow11.battlelog.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class LogCommand implements CommandExecutor {

	File parent;
	Config config;
	String[] outcome = new String[]{"win", "loss", "dq"};

	public LogCommand(File parent, Config config) {
		this.parent = parent;
		this.config = config;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can run this command.");
			return false;
		}

		if (!sender.hasPermission("battlelog.log")) {
			sender.sendMessage(ChatColor.RED + "You don't have permission to run this command.");
			return false;
		}

		if (args.length != 3) {
			sender.sendMessage(ChatColor.RED + "Incorrect number of arguments. See usage.");
			showUsage(sender);
			return false;
		}

		if (!Arrays.asList(config.getGyms()).contains(args[0])) {
			sender.sendMessage(ChatColor.RED + "Gym name doesn't exist");
			return false;
		}

		if (!Arrays.asList(outcome).contains(args[2])) {
			sender.sendMessage(ChatColor.RED + "Please choose from win, loss or dq.");
			showUsage(sender);
			return false;
		}

		String pattern = "dd/MM/yyyy HH:mm:ss";
		DateFormat df = new SimpleDateFormat(pattern);

		Utils.writeLog(parent, new String[]{df.format(new Date()), sender.getName(), args[0], args[1], args[2]});
		sender.sendMessage(ChatColor.GREEN + "Battle has been logged.");
		return false;
	}

	private void showUsage(CommandSender sender) {
		sender.sendMessage(ChatColor.YELLOW + "Usage:\n" +
				"logbattle <gym> <challenger> [win/loss/dq]");
	}

}
