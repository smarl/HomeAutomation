package com.brokenneon.homeautomation.bean;

import java.util.ArrayList;
import java.util.List;

public class Level {

	private int level;
	private String display;
	private boolean active;

	public Level(int level, String display, boolean active) {
		this.level = level;
		this.display = display;
		this.active = active;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public static List<Level> buildLevels(Device... devices) {
		List<Level> result = new ArrayList<Level>();
		result.add(new Level(0, "Off", hasDeviceOn(devices) == false));
		result.add(new Level(1, "|", hasDeviceOn(devices) && hasValuesBetween(devices, 0, 10)));
		result.add(new Level(15, "|", hasDeviceOn(devices) && hasValuesBetween(devices, 10, 25)));
		result.add(new Level(30, "|", hasDeviceOn(devices) && hasValuesBetween(devices, 25, 40)));
		result.add(new Level(45, "|", hasDeviceOn(devices) && hasValuesBetween(devices, 40, 45)));
		result.add(new Level(60, "|", hasDeviceOn(devices) && hasValuesBetween(devices, 45, 70)));
		result.add(new Level(80, "|", hasDeviceOn(devices) && hasValuesBetween(devices, 70, 90)));
		result.add(new Level(100, "On", hasDeviceOn(devices) && hasValuesBetween(devices, 90, 101)));
		return result;
	}

	private static boolean hasDeviceOn(Device[] devices) {
		for (Device d : devices) {
			if (d.isOn()) {
				return true;
			}
		}
		return false;
	}

	private static boolean hasValuesBetween(Device[] devices, int min, int under) {
		for (Device d : devices) {
			if (d.getLevel() >= min && d.getLevel() < under) {
				return true;
			}
		}
		return false;
	}

}
