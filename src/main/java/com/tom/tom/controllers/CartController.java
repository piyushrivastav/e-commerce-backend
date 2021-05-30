package com.tom.tom.controllers;

import com.tom.tom.dto.CartDTO;
import com.tom.tom.entities.Cart;
import com.tom.tom.entities.Client;
import com.tom.tom.entities.Product;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.services.CartService;
import com.tom.tom.services.ClientService;
import com.tom.tom.services.ProductService;
import com.tom.tom.utilities.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/view/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findItemsInCart(@PathVariable Integer clientId) throws ECommerceServiceException {

        Cart cart = cartService.findItemsForClient(clientId);
        CartDTO cartDTO = DTOConverter.converCartToDTO(cart);
        return ResponseEntity.ok().body(cartDTO);
    }

    @RequestMapping(value = "/removeAllProducts/{clientId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> remove(@PathVariable("clientId") Integer clientId) throws ECommerceServiceException {
        boolean isCleared = cartService.clearCartForClient(clientId);
        return ResponseEntity.ok().body("{\"message\": \"cart is cleared for client\"}");
    }

    @RequestMapping(value = "/addProduct/{clientId}/{productId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addProduct(@PathVariable("productId") Integer productId, @PathVariable("clientId") Integer clientId) throws ECommerceServiceException {
        Product product = productService.find(productId);
        Client client = clientService.find(clientId);
        Cart cart = cartService.getCartForClient(clientId);
        List<Product> cartProducts = cart.getCartContents();
        if (cartProducts.contains(product)) {
            cart = cartService.addQuantityofProduct(product.getId(), cart.getId());
        } else {
            cartProducts.add(product);
            Map<Integer, Integer> map = cart.getProductQuantityMap();
            map.put(productId, 1);
            cart.setProductQuantityMap(map);
        }
        cart.setCartContents(cartProducts);
        cartService.add(cart);
        CartDTO cartDTO = DTOConverter.converCartToDTO(cart);
        return ResponseEntity.ok().body(cartDTO);
    }

    @RequestMapping(value = "/removeProduct/{clientId}/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeAProduct(@PathVariable("productId") Integer productId, @PathVariable("clientId") Integer clientId) throws ECommerceServiceException {
        Product product = productService.find(productId);
        Cart cart = cartService.getCartForClient(clientId);
        List<Product> cartProducts = cart.getCartContents();
        cartProducts.remove(product);
        Map<Integer, Integer> map = cart.getProductQuantityMap();
        map.remove(productId);
        cart.setProductQuantityMap(map);
        cart.setCartContents(cartProducts);
        cartService.add(cart);
        CartDTO cartDTO = DTOConverter.converCartToDTO(cart);
        return ResponseEntity.ok().body(cartDTO);
    }
}
