package com.brokenneon.homeautomation.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.brokenneon.homeautomation.ConnectedActions;
import com.brokenneon.homeautomation.ConnectedDAO;
import com.brokenneon.homeautomation.bean.Device;
import com.brokenneon.homeautomation.bean.House;
import com.brokenneon.homeautomation.bean.Room;

public class ActionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2162012252891659683L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if ("dim".equals(action)) {
			dim(req);
		}

		resp.getWriter().write("Ok");
	}

	public void dim(HttpServletRequest req) throws ServletException,
			IOException {
		int level = Integer.parseInt(req.getParameter("level"));
		if (req.getParameter("house") == null && req.getParameter("room") == null && req.getParameter("did") == null) {
			throw new ServletException("house nor room nor did were specified!");
		}

		House house = ConnectedDAO.getHouse();
		List<Device> devices = new ArrayList<Device>();
		if (req.getParameter("house") != null) {
			for (Room r : house.getRooms()) {
				devices.addAll(r.getDevices());
			}
		} else if (req.getParameter("room") != null) {
			devices = house.getRoom(req.getParameter("room")).getDevices();
		} else {
			devices.add(house.getDevices().get(req.getParameter("did")));
		}

		if (level == 0) {
			ConnectedActions.off(devices.toArray(new Device[devices.size()]));
		} else {
			ConnectedActions.on(level,
					devices.toArray(new Device[devices.size()]));
		}
	}
}
