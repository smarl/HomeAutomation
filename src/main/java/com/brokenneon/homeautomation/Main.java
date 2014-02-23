package com.brokenneon.homeautomation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import javax.servlet.ServletException;

import org.apache.catalina.startup.Tomcat;

public class Main {
	public static final String ZEROCONF_NAME = "homeautomation";
	public static ServiceInfo serviceInfo;
	public static JmDNS jmdns;
	public static int webPort;

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = setUpTomcat();

		setUpZeroConf();

		tomcat.start();
		tomcat.getServer().await();
	}

	private static Tomcat setUpTomcat() throws ServletException {
		String webappDirLocation = "src/main/webapp/";

		Tomcat tomcat = new Tomcat();
		webPort = 8081;

		String webPortString = System.getenv("PORT");
		if (webPortString != null && !webPortString.isEmpty()) {
			webPort = Integer.valueOf(webPortString);
		}
		tomcat.setPort(webPort);

		tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: "
				+ new File("./" + webappDirLocation).getAbsolutePath());

		return tomcat;
	}

	private static void setUpZeroConf() throws IOException {
		Map<String, String> props = new HashMap<String, String>();
		props.put("path", "/");

		serviceInfo = ServiceInfo.create("_http._tcp.local.", ZEROCONF_NAME,
				webPort, "Home Automation using Connected by TCP");
		serviceInfo.setText(props);
		System.out.println("Registering zerconf service " + ZEROCONF_NAME
				+ " on port " + Integer.toString(webPort));

		jmdns = JmDNS.create("homeautomation");
		jmdns.registerService(serviceInfo);

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Unregistering zerconf service "
						+ ZEROCONF_NAME);
				jmdns.unregisterService(serviceInfo);
			}
		});
	}
}
