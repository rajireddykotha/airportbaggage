package com.barclays.baggagerouting.common;

import java.util.Date;

/**
 * Customized log. In real time use Log4j or something else
 * @author rajir
 *
 */
public class BaggageRoutingLog {

	private static BaggageRoutingLog baggaeRoutingLog = null;
	
	public static synchronized BaggageRoutingLog getInstance() {
		if(baggaeRoutingLog != null) {
			baggaeRoutingLog = new BaggageRoutingLog();
		}
		return baggaeRoutingLog;
	}
	
	public static void debug(String log) {
		System.out.println(new Date() + ":"  +log);
	}
	
	public static void error(String log) {
		System.out.println(new Date() + ":"  +log);
	}
}
