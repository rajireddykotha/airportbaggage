package com.barclays.baggagerouting;

import org.junit.Test;

import com.barclays.baggagerouting.BaggageRoute;

import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Unit testing few of the basic scenarios for test 
 */
public class BaggageRoutingUnitTest {

	BaggageRoute baggageRoute = new BaggageRoute();
	
    @Test
    public void assertEaqual() {
    	List<String> expectedOutput = Arrays.asList("0001 Concourse_A_Ticketing A5 A1 : 11", 
    												"0002 A5 A1 A2 A3 A4 : 9",
    												"0003 A2 A1 : 1", 
    												"0004 A8 A9 A10 A5 : 6",
    												"0005 A7 A8 A9 A10 A5 BaggageClaim : 12");
    	
    	Assert.assertEquals(expectedOutput, baggageRoute.findShortestPath());
     }
    
    @Test
    public void assertNotEaqual() {
    	List<String> expectedOutput = Arrays.asList("0001 Concourse_A_Ticketing A5 A1 : 11", 
    												"0002 A5 A1 A2 A3 A4 : 9",
    												"0003 A2 A1 : 0", 
    												"0004 A8 A9 A10 A5 : 6",
    												"0005 A7 A8 A9 A10 A5 BaggageClaim : 12");
    	
    	Assert.assertNotSame(expectedOutput,baggageRoute.findShortestPath());
     }
    
    @Test
    public void assertNotNull() {
    	
    	Assert.assertNotNull(baggageRoute.findShortestPath());
    }
    
}