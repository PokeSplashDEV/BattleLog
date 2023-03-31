package com.bencrow11.battlelog;

import com.bencrow11.battlelog.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Utils {

	public static Config getConfig(File parent) {

		Config config = new Config();
		try {
			parent.mkdir();
			File file = new File(parent, "config.json");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			if (!file.exists()) {
				file.createNewFile();
				Writer writer = new FileWriter(file, false);
				gson.toJson(config, writer);
				writer.flush();
				writer.close();

				System.out.println("Config not found, Created Config.");
			}
			Reader reader = new FileReader(file);
			config = gson.fromJson(reader, Config.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return config;
	}

	public static void writeLog(File parent, String[] data) {
		try {
			parent.mkdir();
			File file = new File(parent, "logs.csv");
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("Data file not found, creating data file.");
			}
			Writer writer = new FileWriter(file, true);
			writer.flush();

			StringBuilder line = new StringBuilder();
			for (int i = 0; i < data.length; i++) {
				line.append("\"");
				line.append(data[i].replaceAll("\"","\"\""));
				line.append("\"");
				if (i != data.length - 1) {
					line.append(',');
				}
			}
			line.append("\n");
			writer.write(line.toString());
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
