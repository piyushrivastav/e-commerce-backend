package com.tom.tom.dto;

import java.util.ArrayList;
import java.util.List;

public class InventoryDTO {

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
