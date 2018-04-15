package com.barclays.baggagerouting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.barclays.baggagerouting.bean.Bags;
import com.barclays.baggagerouting.bean.Conveyor;
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
 * This is Main class to start application 
 * 
 * Expected Output for given Input file:
 *	0001 Concourse_A_Ticketing A5 A1 : 11
 *	0002 A5 A1 A2 A3 A4 : 9
 *	0003 A2 A1 : 1
 *	0004 A8 A9 A10 A5 : 6
 *	0005 A7 A8 A9 A10 A5 BaggageClaim : 12
 */
public class App 
{
    public static void main( String[] args )
    {
    	BaggageRoute baggageRoute = new BaggageRoute();
    	List<String> outputList = baggageRoute.findShortestPath();
    	for(String path:outputList) {
    		System.out.println(path);
    	}
    }
    
}

