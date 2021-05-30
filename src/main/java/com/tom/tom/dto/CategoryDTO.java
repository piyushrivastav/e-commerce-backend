package com.tom.tom.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CategoryDTO implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	private List<ProductDTO> products = new ArrayList<>();

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProduts(List<ProductDTO> produt) {
		this.products = produt;
	}

}
