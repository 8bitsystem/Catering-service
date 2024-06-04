package com.Catering_Server.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Catering_Server.Entity.Customer;
import com.Catering_Server.Entity.Items;
import com.Catering_Server.Entity.Venue;
import com.Catering_Server.Repository.CustomerRepository;
import com.Catering_Server.Service.VenueService;
import com.Catering_Server.dto.CreateVenueRequest;
//import com.Catering_Server.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/venue")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	  @Autowired
	  private CustomerRepository customerRepository; // Assuming for customer lookup

	  
//	  @GetMapping("/customer/{customerId}")
//	  public ResponseEntity<List<Venue>> getVenuesByCustomerId(@PathVariable Long customerId) {
//	      try {
//	          List<Venue> venues = venueService.getVenuesByCustomerId(customerId);
//	          return ResponseEntity.ok(venues);
//	      } catch (Exception e) {
//	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	      }
//	  }

		@GetMapping("/getById/{customer_id}")
		public ResponseEntity<List<Venue>> getItemById(@PathVariable Long customer_id) throws ChangeSetPersister.NotFoundException {
			System.out.println(customer_id);
			List<Venue> item = venueService.getVenuesByCustomerId(customer_id);
			return ResponseEntity.ok(item);
		}
	
	@GetMapping("/get")
    public ResponseEntity<List<Venue>> getAllVenues() {
        try {
            List<Venue> venues = venueService.getAllVenues();
            return ResponseEntity.ok(venues);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	
	@PostMapping("/save")
	public ResponseEntity<List<Venue>> createVenues(@RequestBody CreateVenueRequest request) {
	    Customer customer = customerRepository.findById(request.getCustomerId()).get(); // Use custom except
	    List<Venue> createdVenues = venueService.createVenues(customer, request.getVenues());
	    return new ResponseEntity<>(createdVenues, HttpStatus.CREATED);
	    }
	}
