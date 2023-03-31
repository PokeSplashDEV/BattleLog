package com.bencrow11.battlelog.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.Arrays;
import java.util.List;

public class LogTab implements TabCompleter {

	String[] gyms;
	String[] outcome = new String[]{"win", "loss", "dq"};

	public LogTab(String[] gyms) {
		this.gyms = gyms;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {


		if (args.length == 1) {
			return Arrays.asList(gyms);
		} else if (args.length == 3) {
			return Arrays.asList(outcome);
		}

		return null;
	}
}
