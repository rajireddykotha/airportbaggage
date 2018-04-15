package com.barclays.baggagerouting.common;

/**
 * Customized exception. This can enhance but keeping short for this test
 * @author rajir
 *
 */
public class BaggageRoutingException extends Exception {

	public BaggageRoutingException(){
	}
	public BaggageRoutingException(String msg){
		super(msg);
		BaggageRoutingLog.error(msg);
	}
	
}
