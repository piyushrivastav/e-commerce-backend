package com.tom.tom.controllers;

import com.tom.tom.entities.Product;
import com.tom.tom.dto.ProductDTO;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.services.ProductService;
import com.tom.tom.utilities.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findProductById(@PathVariable Integer id) throws ECommerceServiceException {
        Product product = productService.find(id);
        ProductDTO productDTO = DTOConverter.convertProductToDTO(product);
        return ResponseEntity.ok().body(productDTO);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllProducts() throws ECommerceServiceException {
        List<Product> products = productService.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product :
                products) {
            ProductDTO productDTO = DTOConverter.convertProductToDTO(product);
            productDTOList.add(productDTO);
        }
        return ResponseEntity.ok().body(productDTOList);
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postProductToInventory(@Valid @RequestBody List<ProductDTO> productList) throws ECommerceServiceException {
        for (ProductDTO productDTO : productList) {
            Product product = DTOConverter.convertDTOToProduct(productDTO);
            Product existingProduct = productService.findByName(productDTO.getName());
            if (existingProduct == null) {
                productService.save(product);
            } else {
                product.setId(existingProduct.getId());
                productService.save(product);
            }
        }
        return ResponseEntity.ok().body("{\"message\",\"Product(s) successfully added to Inventory\"}");
    }
}
