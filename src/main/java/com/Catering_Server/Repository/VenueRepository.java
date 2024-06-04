package com.Catering_Server.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Catering_Server.Entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer>{

	 @Query("SELECT v FROM Venue v WHERE v.customer.id = :customerId")
	    List<Venue> findByCustomerId(@Param("customerId") Long customerId);
}