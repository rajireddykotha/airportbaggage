package com.barclays.baggagerouting.factory;

import com.barclays.baggagerouting.handlers.BagHandler;
import com.barclays.baggagerouting.handlers.ConveyorHandler;
import com.barclays.baggagerouting.handlers.DepartureHandler;
/**
 * Factory implementation for Input Handlers
 * @author rajir
 *
 */
public class InputFactory {
	/**
	 * Get the handler for the input type
	 * @param type
	 * @return
	 */
	public static InputHandler getHandler(String type) {
		if(type == null) return null;
		else if(type.equals("Conveyor")) return new ConveyorHandler();
		else if(type.equals("Departures")) return new DepartureHandler();
		else if(type.equals("Bags")) return new BagHandler(); 
		else return null;
	}
}
