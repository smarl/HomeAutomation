package com.brokenneon.homeautomation.bean;

import java.util.ArrayList;
import java.util.List;

public class Room extends Actionable {

	private List<Device> devices = new ArrayList<Device>();
	private String name;
	private String rid;

	public List<Device> getDevices() {
		return devices;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addDevice(Device d) {
		devices.add(d);
	}

	public String getName() {
		return name;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public List<Level> getLevelDisplay() {
		return Level.buildLevels(this.devices.toArray(new Device[this.devices
				.size()]));
	}

	public boolean isOnline() {
		for (Device d : devices) {
			if (d.isOnline()) {
				return true;
			}
		}
		return false;
	}

	public Integer getAverageLevel() {
		int count = 0;
		int total = 0;
		for (Device d : getDevices()) {
			count++;
			if (d.isOnline()) {
				total += d.getLevel();
			}
		}
		return Math.round(total / count);
	}

	@Override
	public String getActionableTag() {
		return "rid";
	}

	@Override
	public String getActionableId() {
		return getRid();
	}

	@Override
	public String getActionableRequstAction() {
		return "RoomSendCommand";
	}
}
