package com.barclays.baggagerouting.bean;

public class Bags {
	
	private String bagNum;
	private String entrypoint;
	private String flightId;
	
	public String getBagNum() {
		return bagNum;
	}
	public void setBagNum(String bagNum) {
		this.bagNum = bagNum;
	}
	public String getEntrypoint() {
		return entrypoint;
	}
	public void setEntrypoint(String entrypoint) {
		this.entrypoint = entrypoint;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
}
