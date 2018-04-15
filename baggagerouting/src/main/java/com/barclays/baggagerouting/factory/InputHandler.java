package com.barclays.baggagerouting.factory;

import java.io.IOException;
import java.util.Map;

import com.barclays.baggagerouting.common.BaggageRoutingException;

public interface InputHandler {
	public Map<String,Object> processInput() throws BaggageRoutingException;
	//public Map<String,Object> getResult();
}
