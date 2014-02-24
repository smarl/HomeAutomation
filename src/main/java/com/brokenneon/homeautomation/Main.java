package com.brokenneon.homeautomation;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.startup.Tomcat;

public class Main {
	public static int webPort;

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = setUpTomcat();

		ZeroConf.setUpZeroConf(webPort);

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

}
