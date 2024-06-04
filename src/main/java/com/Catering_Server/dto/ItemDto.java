package com.Catering_Server.dto;

public class ItemDto {

	private Long itemsId;

	private String itemName;

	private String itemDescription;

	public Long getItems_id() {
		return itemsId;
	}

	public void setItems_id(Long itemsId) {
		this.itemsId = itemsId;
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

	public ItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemDto(Long itemsId, String itemName, String itemDescription) {
		super();
		this.itemsId = itemsId;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
	}

	@Override
	public String toString() {
		return "ItemDto [itemsiId=" + itemsId + ", itemName=" + itemName + ", itemDescription=" + itemDescription
				+ "]";
	}
	
}
