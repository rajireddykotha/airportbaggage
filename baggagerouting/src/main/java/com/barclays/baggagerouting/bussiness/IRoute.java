package com.barclays.baggagerouting.bussiness;

import java.util.Set;
/**
 * 
 * @author rajir
 *
 */
public interface IRoute  {
	public Graph calculateShortestPathFromSource(Graph graph, Node source);
}
