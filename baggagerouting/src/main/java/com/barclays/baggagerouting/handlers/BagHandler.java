package com.barclays.baggagerouting.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.barclays.baggagerouting.bean.Bags;
import com.barclays.baggagerouting.common.BaggageRoutingException;
import com.barclays.baggagerouting.common.BaggageRoutingLog;
import com.barclays.baggagerouting.factory.InputHandler;
import com.barclays.baggagerouting.util.StringUtil;

public class BagHandler implements InputHandler {

	/**
	 * Construct Bags 
	 */
	public Map<String,Object> processInput() throws BaggageRoutingException {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = new TreeMap<String,Object>();
		InputStream inStream = null;
		BufferedReader reader = null;
		try {
			inStream = this.getClass().getResourceAsStream("/input.txt");
			reader = new BufferedReader(new InputStreamReader(inStream));
		     String line;
		     Bags bags = null;
		     boolean isRead = false;
		     while ((line = reader.readLine()) != null) {
		    	 if(isRead && StringUtil.isNotEmpty(line) && !line.contains("#")) {
		    		// System.out.println(line+":");
		    		 String[] tokens = line.split(" ");
		    		 bags = new Bags();
		    		 bags.setBagNum(tokens[0]);
		    		 bags.setEntrypoint(tokens[1]);
		    		 bags.setFlightId(tokens[2]);
		    		 resultMap.put(tokens[0], bags);
		    	 }
		    	 if(StringUtil.isNotEmpty(line) && line.trim().equalsIgnoreCase("# Section: Bags")) {
		    		 isRead = true;
		    	 } else if(StringUtil.isNotEmpty(line) &&  line.contains("#")) {
		    		 isRead = false;
		    	 }
		     }
		}catch(NullPointerException npe) {
			BaggageRoutingLog.error(npe.getMessage());
		}catch(ArrayIndexOutOfBoundsException aio) {
			BaggageRoutingLog.error(aio.getMessage());
		}catch(Exception ioe) {
			BaggageRoutingLog.error(ioe.getMessage());
		}
		finally {
			
				try {
					if(inStream != null) inStream.close();
					if(reader != null ) reader.close();
				} catch (IOException e) {
					BaggageRoutingLog.error(e.getMessage());
				}
		}
	return resultMap;
	}
	
}
