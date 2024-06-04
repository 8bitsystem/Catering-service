package com.Catering_Server.dto;

import java.util.List;

public class ItemsDto {

	private Long items_id;

	private String itemName;

	private String itemDescription;
//
//    private Date createdOn;

//    private Date updatedOn;

	private List<Long> categoryIds;

	public ItemsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemsDto(Long items_id, String itemName, String itemDescription, List<Long> categoryIds) {
		super();
		this.items_id = items_id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.categoryIds = categoryIds;
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

	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	@Override
	public String toString() {
		return "ItemsDto [items_id=" + items_id + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ ", categoryIds=" + categoryIds + "]";
	}

}
