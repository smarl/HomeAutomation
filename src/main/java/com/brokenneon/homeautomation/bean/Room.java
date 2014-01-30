package com.brokenneon.homeautomation.bean;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private List<Device> devices = new ArrayList<Device>();
	private String name;

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

	public List<Level> getLevelDisplay() {
		return Level.buildLevels(this.devices.toArray(new Device[this.devices
				.size()]));
	}

	public boolean isOnline() {
		for(Device d : devices){
			if( d.isOnline() ) {
				return true;
			}
		}
		return false;
	}
}
