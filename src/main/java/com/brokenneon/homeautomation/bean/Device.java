package com.brokenneon.homeautomation.bean;

import java.util.ArrayList;
import java.util.List;

public class Device {
	String did;
	String name;
	int level;
	boolean on = false;
	boolean online = false;

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public List<Level> getLevelDisplay() {
		return Level.buildLevels(this);
	}
}
