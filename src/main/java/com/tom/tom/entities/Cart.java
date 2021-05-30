package com.tom.tom.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer clientId;
    @ElementCollection
    @CollectionTable(name = "products")
    private List<Product> cartContents;

    public Map<Integer, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Integer, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    @ElementCollection
    @CollectionTable(name = "productQuantityMap")
    private Map<Integer,Integer> productQuantityMap;


    public List<Product> getCartContents() {
        return cartContents;
    }

    public void setCartContents(List<Product> cartContents) {
        this.cartContents = cartContents;
    }


    public Cart() {

    }

    public Cart(Integer id, Integer clientId) {
        super();
        this.id = id;
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cart other = (Cart) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
