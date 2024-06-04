package com.Catering_Server.dto;

public class CartDto {
	
	private Long cart_id;
	
	private Long item_id;

	public Long getCart_id() {
		return cart_id;
	}

	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}

	public Long getItem_id() {
		return item_id;
	}

	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}

	public CartDto(Long cart_id, Long item_id) {
		super();
		this.cart_id = cart_id;
		this.item_id = item_id;
	}

	public CartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CartDto [cart_id=" + cart_id + ", item_id=" + item_id + "]";
	}
	
	

}
