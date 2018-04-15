package com.barclays.baggagerouting.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.barclays.baggagerouting.bussiness.Node;
import com.barclays.baggagerouting.common.BaggageRoutingException;
import com.barclays.baggagerouting.common.BaggageRoutingLog;
import com.barclays.baggagerouting.factory.InputHandler;
import com.barclays.baggagerouting.util.StringUtil;

public class ConveyorHandler implements InputHandler {
	
	/**
	 * Process Conveyor handler and also construct the adjacent nodes
	 */
	public Map<String,Object> processInput() throws BaggageRoutingException {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		InputStream inStream = null;
		BufferedReader reader = null;
		try {
			inStream = this.getClass().getResourceAsStream("/input.txt");
			reader = new BufferedReader(new InputStreamReader(inStream));
		    String line;
		    Node sourceNode = null, targetNode = null;
		    boolean isRead = false;
		    while ((line = reader.readLine()) != null) {
		    	 if(isRead && StringUtil.isNotEmpty(line) && !line.contains("#")) {
		    		//System.out.println(line+":");
		    		 String[] tokens = line.split(" ");
	    			 
	    			 sourceNode = addNode(tokens[0],resultMap);
	    			 targetNode = addNode(tokens[1],resultMap);
	    			 //Bi-directional process
	    			 sourceNode.addDestination(targetNode, Integer.valueOf(tokens[2]));
	    			 targetNode.addDestination(sourceNode, Integer.valueOf(tokens[2]));
	    	 	 }
		    	 if (StringUtil.isNotEmpty(line) && line.trim().equalsIgnoreCase("# Section: Conveyor System")) {
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
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	private Node addNode(String name, Map<String,Object> resultMap) {
		if(!resultMap.containsKey(name)) {
			resultMap.put(name, new Node(name));
		}
		return (Node)resultMap.get(name);
	}
	
}
