package com.tom.tom.dto;

import com.tom.tom.entities.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class CartDTO  {

    private Integer id;
    private Integer clientId;
    private List<ProductDTO> cartContents;

    public Map<Integer, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Integer, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    private Map<Integer,Integer> productQuantityMap;


    public List<ProductDTO> getCartContents() {
        return cartContents;
    }

    public void setCartContents(List<ProductDTO> cartContents) {
        this.cartContents = cartContents;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
