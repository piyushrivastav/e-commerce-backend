package com.tom.tom.services;

import com.tom.tom.entities.Product;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product find(Integer id) throws ECommerceServiceException {
        try {
            Optional<Product> obj = productRepository.findById(id);
            return obj.get();
        }catch (Exception exception){
            throw new ECommerceServiceException("Exception while finding a product in repo",exception);
        }
    }

    public List<Product> findAll() throws ECommerceServiceException {
        try {
            Optional<List<Product>> obj = Optional.of(productRepository.findAll());
            return obj.get();
        }catch (Exception exception){
            throw new ECommerceServiceException("Exception while finding all the products in repo",exception);
        }


    }

    public Product save(Product product) throws ECommerceServiceException {
        try {
            return productRepository.save(product);
        } catch (Exception exception) {
            throw new ECommerceServiceException("Exception while saving product", exception);
        }

    }

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
}