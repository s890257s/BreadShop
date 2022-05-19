package tw.com.eeit.breadshop.shop.model.bean;

import java.io.Serializable;

import tw.com.eeit.breadshop.user.model.bean.Users;

public class ShoppingCart implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private Users users;
	private Products products;
	private int quantity;

	public ShoppingCart() {
	}
	
	public ShoppingCart(String id, Users users, Products products, int quantity) {
		super();
		this.id = id;
		this.users = users;
		this.products = products;
		this.quantity = quantity;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
