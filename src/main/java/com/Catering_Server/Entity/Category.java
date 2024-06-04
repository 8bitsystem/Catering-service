package com.Catering_Server.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;


@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long category_id;

    private String categoryName;
    
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<Items> items;
    
    public Category() {
		super();
		
	}

	public Category(Long category_id, String categoryName, List<Items> items) {
		super();
		this.category_id = category_id;
		this.categoryName = categoryName;
		this.items = items;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
    
}
