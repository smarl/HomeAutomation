package com.brokenneon.homeautomation.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.brokenneon.homeautomation.bean.Device;
import com.brokenneon.homeautomation.bean.House;
import com.brokenneon.homeautomation.bean.Room;

public class HouseParser {

	public static House parse(InputStream is) throws IOException {
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(is);
			NodeList roomNodes = (NodeList) XPathFactory
					.newInstance()
					.newXPath()
					.evaluate(
							"/gwrcmds/gwrcmd[string(gcmd)='RoomGetCarousel']/gdata/gip/room",
							doc, XPathConstants.NODESET);

			House result = new House();

			for (int i = 0; i < roomNodes.getLength(); i++) {
				result.addRoom(buildRoom(roomNodes.item(i)));
			}

			return result;
		} catch (Exception e) {
			throw new IOException(e);
		} finally {
			is.close();
		}
	}

	private static Room buildRoom(Node roomNode) {
		Room result = new Room();
		for (int i = 0; i < roomNode.getChildNodes().getLength(); i++) {
			Node child = roomNode.getChildNodes().item(i);
			if ("rid".equals(child.getNodeName())) {
				result.setRid(child.getTextContent());
			}
			if ("name".equals(child.getNodeName())) {
				result.setName(child.getTextContent());
			}
			if ("device".equals(child.getNodeName())) {
				result.addDevice(parseDevice(child));
			}
		}
		return result;
	}

	private static Device parseDevice(Node deviceNode) {
		Device result = new Device();
		for (int i = 0; i < deviceNode.getChildNodes().getLength(); i++) {
			Node child = deviceNode.getChildNodes().item(i);
			if ("name".equals(child.getNodeName())) {
				result.setName(child.getTextContent());
			}
			if ("did".equals(child.getNodeName())) {
				result.setDid(child.getTextContent());
			}
			if ("state".equals(child.getNodeName())) {
				result.setOn("1".equals(child.getTextContent()));
			}
			if ("level".equals(child.getNodeName())) {
				result.setOnline(false);
				try {
					result.setLevel(Integer.parseInt(child.getTextContent()));
					result.setOnline(true);
				} catch (Exception e) {

				}
			}
		}
		return result;
	}

}
