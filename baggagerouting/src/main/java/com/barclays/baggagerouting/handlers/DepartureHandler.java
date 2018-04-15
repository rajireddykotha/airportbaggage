package com.barclays.baggagerouting.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.barclays.baggagerouting.bean.Departures;
import com.barclays.baggagerouting.bussiness.Node;
import com.barclays.baggagerouting.common.BaggageRoutingException;
import com.barclays.baggagerouting.common.BaggageRoutingLog;
import com.barclays.baggagerouting.factory.InputHandler;
import com.barclays.baggagerouting.util.StringUtil;

public class DepartureHandler implements InputHandler {

	/**
	 * Construct Departures
	 */
	public Map<String,Object> processInput() throws BaggageRoutingException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		InputStream inStream = null;
		BufferedReader reader = null;
		try {
			inStream = this.getClass().getResourceAsStream("/input.txt");
			reader = new BufferedReader(new InputStreamReader(inStream));
		     String line;
		     Departures departures = null;
		     boolean isRead = false;
		     while ((line = reader.readLine()) != null) {
		    	 if(isRead && StringUtil.isNotEmpty(line) && !line.contains("#")) {
		    		// System.out.println(line+":");
		    		 String[] tokens = line.split(" ");
		    		 departures = new Departures();
		    		 departures.setFlightId(tokens[0]);
		    		 departures.setFlightGate(tokens[1]);
		    		 departures.setDestination(tokens[2]);
		    		 departures.setFlightTime(tokens[3]);
		    		 resultMap.put(tokens[0], departures);
		    	 }
		    	 if(StringUtil.isNotEmpty(line) && line.trim().equalsIgnoreCase("# Section: Departures")) {
		    		 isRead = true;
		    	 } else if(StringUtil.isNotEmpty(line) && line.contains("#")) {
		    		 isRead = false;
		    	 }
		     }
		}catch(NullPointerException npe) {
			BaggageRoutingLog.error(npe.getMessage());
		}catch(Exception ioe) {
			BaggageRoutingLog.error(ioe.getMessage());
		}
		finally {
			
				try {
					if(inStream != null) inStream.close();
					if(reader != null ) reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return resultMap;
	}

}
