package com.brokenneon.homeautomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ZeroConf {
	private static final String HTTP_TCP_LOCAL = "_http._tcp.local.";
	public static final String ZEROCONF_NAME = "homeautomation";
	// public static DNSSDRegistration reg;

	public static ServiceInfo serviceInfo;
	public static JmDNS jmdns;

	// public void setUpZeroConfApple(int webPort) throws DNSSDException {
	// reg = DNSSD.register(0, DNSSD.ALL_INTERFACES, ZEROCONF_NAME,
	// HTTP_TCP_LOCAL, null, // Name, type, and domain
	// null, webPort, // Target host and port
	// null, this); // TXT record and listener object
	// }

	// // Display error message on failure
	// public void operationFailed(DNSSDService service, int errorCode) {
	// System.out.println("Registration failed " + errorCode);
	// }
	//
	// // Display registered name on success
	// public void serviceRegistered(DNSSDRegistration registration, int flags,
	// String serviceName, String regType, String domain) {
	// System.out.println("Registered Name  : " + serviceName);
	// System.out.println("           Type  : " + regType);
	// System.out.println("           Domain: " + domain);
	// }

	public static void setUpZeroConf(int webPort) throws IOException {
		Map<String, String> props = new HashMap<String, String>();
		props.put("path", "/");

		serviceInfo = ServiceInfo.create("_http._tcp.local.", ZEROCONF_NAME,
				webPort, ZEROCONF_NAME);

		// serviceInfo = ServiceInfo.create(HTTP_TCP_LOCAL, ZEROCONF_NAME,
		// webPort, 0, 0, true, props);
		// serviceInfo = ServiceInfo.create(HTTP_TCP_LOCAL, ZEROCONF_NAME,
		// webPort, "Home Automation using Connected by TCP");
		serviceInfo.setText(props);
		System.out.println("Registering zerconf service " + "homeautomation."
				+ HTTP_TCP_LOCAL + " on port " + Integer.toString(webPort));

		jmdns = JmDNS.create("homeautomation." + HTTP_TCP_LOCAL);
		jmdns.registerService(serviceInfo);

		// jmdns.addServiceListener(HTTP_TCP_LOCAL, new ServiceListener() {
		// @Override
		// public void serviceResolved(ServiceEvent event) {
		// System.out.println("Service resolved: " + event.toString());
		// }
		//
		// @Override
		// public void serviceRemoved(ServiceEvent event) {
		// System.out.println("Service removed: " + event.toString());
		// }
		//
		// @Override
		// public void serviceAdded(ServiceEvent event) {
		// System.out.println("Service added: " + event.toString());
		// }
		// });

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Unregistering zerconf service "
						+ ZEROCONF_NAME);
				jmdns.unregisterAllServices();
				jmdns.unregisterService(serviceInfo);
				try {
					jmdns.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
