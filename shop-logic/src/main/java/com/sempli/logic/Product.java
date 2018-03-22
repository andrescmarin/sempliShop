package com.sempli.logic;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private String name;
	private String description;
	private int quantity;
	private boolean inStock;
	
	protected Product() {}

	public Product(String name, String description, int quantity, boolean inStock) {
		super();
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.inStock = inStock;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}	

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", quantity=" + quantity
				+ ", inStock=" + inStock + "]";
	}
}
