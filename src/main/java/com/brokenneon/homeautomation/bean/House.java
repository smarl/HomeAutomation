package com.brokenneon.homeautomation.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House extends Actionable {
	List<Room> rooms = new ArrayList<Room>();
	List<Device> allDevices = new ArrayList<Device>();
	Map<String, Device> devices = new HashMap<String, Device>();
	boolean online = false;

	public void addRoom(Room newRoom) {
		rooms.add(newRoom);
		for (Device d : newRoom.getDevices()) {
			devices.put(d.getDid(), d);
			allDevices.add(d);
			if (d.isOnline()) {
				online = true;
			}
		}
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public Map<String, Device> getDevices() {
		return devices;
	}

	public Room getRoom(String name) {
		for (Room r : rooms) {
			if (name.equals(r.getName())) {
				return r;
			}
		}
		return null;
	}

	public List<Level> getLevelDisplay() {
		return Level.buildLevels(allDevices.toArray(new Device[allDevices
				.size()]));
	}

	public boolean isOnline() {
		return online;
	}

	@Override
	public String getActionableTag() {
		return "hid";
	}

	@Override
	public String getActionableId() {
		return "1";
	}

	@Override
	public String getActionableRequstAction() {
		return "HouseSendCommand";
	}
}
