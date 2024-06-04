package com.Catering_Server.Entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Venue")
public class Venue {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int venue_id;

  private String venue;
  private String occasion;
  private Date functionDate;
  private String occasionTime;
  private int numberOfPersons; 

  @ManyToMany(fetch = FetchType.EAGER)
  private List<Category> categories;

  public void addCategory(Category category) {
    categories.add(category);
  }

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;

  public Venue() {
    super();
  }
  
  public Venue(int venue_id, String venue, String occasion, Date functionDate, String occasionTime, int numberOfPersons,
    List<Category> categories, Customer customer) {
    super();
    this.venue_id = venue_id;
    this.venue = venue;
    this.occasion = occasion;
    this.functionDate = functionDate;
    this.occasionTime = occasionTime;
    this.numberOfPersons = numberOfPersons;
    this.categories = categories;
    this.customer = customer;
  }

  public int getVenue_id() {
    return venue_id;
  }

  public void setVenue_id(int venue_id) {
    this.venue_id = venue_id;
  }

  public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public String getOccasion() {
    return occasion;
  }

  public void setOccasion(String occasion) {
    this.occasion = occasion;
  }

  public Date getFunctionDate() {
    return functionDate;
  }

  public void setFunctionDate(Date functionDate) {
    this.functionDate = functionDate;
  }

  public String getOccasionTime() {
    return occasionTime;
  }

  public void setOccasionTime(String occasionTime) {
    this.occasionTime = occasionTime;
  }

  public int getNumberOfPersons() {
    return numberOfPersons;
  }

  public void setNumberOfPersons(int numberOfPersons) {
    this.numberOfPersons = numberOfPersons;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }
}
