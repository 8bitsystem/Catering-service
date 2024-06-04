package com.Catering_Server.dto;

import java.util.List;

import com.Catering_Server.Entity.Venue;

public class CreateVenueRequest {

    private Long customerId;
    private List<Venue> venues;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public List<Venue> getVenues() {
		return venues;
	}
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}
	public CreateVenueRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateVenueRequest(Long customerId, List<Venue> venues) {
		super();
		this.customerId = customerId;
		this.venues = venues;
	}
	@Override
	public String toString() {
		return "CreateVenueRequest [customerId=" + customerId + ", venues=" + venues + "]";
	}

    
}
