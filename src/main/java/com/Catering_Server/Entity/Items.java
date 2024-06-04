package com.Catering_Server.Entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Items")

public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_id")
    private Long items_id;

    private String itemName;
    private String itemDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_on")
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on")
    private Date updatedOn;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "item_category",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    
    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
        this.updatedOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
    }

	
    public Long getId() {
        return items_id;
    }

    public Items() {
		super();
			}
    
	public Items(Long items_id, String itemName, String itemDescription, Date createdOn, Date updatedOn,
			List<Category> categories) {
		super();
		this.items_id = items_id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.categories = categories;
	}

	public Long getItems_id() {
		return items_id;
	}

	public void setItems_id(Long items_id) {
		this.items_id = items_id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
    
    
}