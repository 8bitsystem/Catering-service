package com.Catering_Server.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Catering_Server.Entity.Customer;
import com.Catering_Server.Entity.Items;
import com.Catering_Server.Entity.Venue;
import com.Catering_Server.Repository.VenueRepository;
import com.Catering_Server.exception.ResourceNotFoundException;

@Service
public class VenueService {


	@Autowired
	private VenueRepository venueRepository;
	
	
	   public List<Venue> getAllVenues() {
	        return venueRepository.findAll();
	    }



	  

	   
	       public List<Venue> createVenues(Customer customer, List<Venue> venues) throws ResourceNotFoundException {
	           for (Venue venue : venues) {
	               venue.setCustomer(customer); // Associate venue with customer
	           }

	           return venueRepository.saveAll(venues);
	       } 
	   
	       public List<Venue> getVenuesByCustomerId(Long customerId) {
	    	   System.out.println(customerId);
	    	    return venueRepository.findByCustomerId(customerId);
	    	}




}

