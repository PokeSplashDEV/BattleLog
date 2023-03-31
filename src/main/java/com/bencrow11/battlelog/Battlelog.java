package com.bencrow11.battlelog;

import com.bencrow11.battlelog.commands.LogCommand;
import com.bencrow11.battlelog.commands.LogTab;
import com.bencrow11.battlelog.config.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Battlelog extends JavaPlugin {

	public final File parent = getDataFolder();

	@Override
	public void onEnable() {
		Config config = Utils.getConfig(parent);
		getCommand("logbattle").setExecutor(new LogCommand(parent, config));
		getCommand("logbattle").setTabCompleter(new LogTab(config.getGyms()));
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
