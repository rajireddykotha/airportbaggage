package com.barclays.baggagerouting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.barclays.baggagerouting.bean.Bags;
import com.barclays.baggagerouting.bean.Departures;
import com.barclays.baggagerouting.bussiness.Graph;
import com.barclays.baggagerouting.bussiness.RouteImpl;
import com.barclays.baggagerouting.bussiness.Node;
import com.barclays.baggagerouting.common.BaggageRoutingException;
import com.barclays.baggagerouting.common.BaggageRoutingLog;
import com.barclays.baggagerouting.constants.Constants;
import com.barclays.baggagerouting.factory.InputFactory;

/**
 * 
 * @author rajir
 *
 */
public class BaggageRoute {

	/**
     * First get the data from input file by using handlers
     * Construct the graph with nodes
     * find the shortest path for given input
     */
     public List<String> findShortestPath() {
       	
    	List<String> pathResult = new ArrayList<String>();
    	try {
    		
       		Map<String,Object> bagsMap = InputFactory.getHandler(Constants.BAGS).processInput();
			Map<String,Object> deparuresMap = InputFactory.getHandler(Constants.DEPARTURES).processInput();
			//Map<String,Object> conveyorMap = InputFactory.getHandler("Conveyor").processInput();
			//String[] data = {"A2","A8","A5","Concourse_A_Ticketing"};
			Iterator<String> it = bagsMap.keySet().iterator();  
			while(it.hasNext()) {
				Bags bags = (Bags)bagsMap.get(it.next());
				
				Departures departures =(Departures) deparuresMap.get(bags.getFlightId());
				if(bags.getFlightId().equals(Constants.ARRIVAL)) {
					
					//Departure is null since no mapping and constructs departure in this special case for baggage claim
					departures = new Departures();
					departures.setFlightGate(Constants.BAGGAGE_CLAIM);
				}
				
				Map<String,Object> conveyorMap = InputFactory.getHandler(Constants.CONVEYOR).processInput();
				Node sourceRoutingNode = (Node)conveyorMap.get(bags.getEntrypoint());
				//System.out.println("******"+sourceRoutingNode.getName());
				Graph graph = constructGraph(conveyorMap);	
				graph = new RouteImpl().calculateShortestPathFromSource(graph, sourceRoutingNode);
			
				Iterator<Node> i = graph.getNodes().iterator();
		    	
		    	while(i.hasNext()) {
		    		StringBuffer sb = new StringBuffer(bags.getBagNum()+" ");
		    		//System.out.println(i.next().getShortestPath());
		    		Node rnode = i.next();
		    		//System.out.print(rnode.getName() +" "  );
		    		List<Node> list = rnode.getShortestPath();
		    		for(Node rn:list) {
		    			sb.append(rn.getName()+" ");
		    		}
		    		if(rnode.getName().equals(departures.getFlightGate())){
		    				sb.append(rnode.getName()+" : " + rnode.getDistance());
		    		
		    				pathResult.add(sb.toString());
		    		} else {
		    			
		    		}
		    	}
				
			}
        	} catch (BaggageRoutingException e) {
    			BaggageRoutingLog.error("Error" + e.getMessage());
    		}
    	
    	return pathResult;
     }
     
     /**
      * Build the Graph Object to process shortest path
      * @param conveyorMap
      * @return
      */
     private static Graph constructGraph(Map<String,Object> conveyorMap) {
     	Graph routingGraph = new Graph();
     	Iterator <Map.Entry<String, Object>> it = conveyorMap.entrySet().iterator();
     	while(it.hasNext()) {
     		Map.Entry<String, Object> entry =it.next();
     		Node rn = (Node)entry.getValue();
     	//	System.out.println(rn.getName() + ": " +rn.getAdjacentNodes().toString());
     		routingGraph.addNode((Node)entry.getValue());
     	}
     	//System.out.println(c);
     	return routingGraph;
     }

 
}
