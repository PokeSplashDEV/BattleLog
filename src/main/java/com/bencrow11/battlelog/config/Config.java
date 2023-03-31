package com.bencrow11.battlelog.config;

public class Config {
	private final String[] gyms;

	public Config(String[] gyms) {
		this.gyms = gyms;
	}

	public Config() {
		this.gyms = new String[]{"1-Fighting", "2-Fairy", "3-Grass"};
	}


	public String[] getGyms() {
		return gyms;
	}
}
