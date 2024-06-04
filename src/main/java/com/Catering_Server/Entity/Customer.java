package com.Catering_Server.Entity;

import jakarta.persistence.*;
import lombok.Data;
import javax.validation.constraints.Pattern;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customer_id;

    private String customerName;
    
    @Pattern(regexp="\\d{10}")
    private Long customerPhone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Venue> venues;

    
    public Customer() {
		super();
			}
    
	public Customer(Long customer_id, String customerName, Long customerPhone, List<Venue> venues) {
		super();
		this.customer_id = customer_id;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.venues = venues;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(Long customerPhone) {
		this.customerPhone = customerPhone;
	}

	public List<Venue> getVenues() {
		return venues;
	}

	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}
    
    
}