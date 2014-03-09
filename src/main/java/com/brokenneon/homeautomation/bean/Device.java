package com.brokenneon.homeautomation.bean;

import java.util.List;

public class Device extends Actionable {
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
		return on && online ? level : 0;
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

	@Override
	public String getActionableTag() {
		return "did";
	}

	@Override
	public String getActionableId() {
		return getDid();
	}

	@Override
	public String getActionableRequstAction() {
		return "DeviceSendCommand";
	}	
}
