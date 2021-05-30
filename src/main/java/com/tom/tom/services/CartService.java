package com.tom.tom.services;

import com.tom.tom.dto.CartOrderDTO;
import com.tom.tom.entities.Cart;
import com.tom.tom.entities.CartOrder;
import com.tom.tom.entities.Client;
import com.tom.tom.entities.Product;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.repositories.CartRepository;
import com.tom.tom.repositories.ClientRepository;
import com.tom.tom.repositories.OrderRepository;
import com.tom.tom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;


    public List<CartOrder> findAll() {
        return orderRepository.findAll();
    }

    public boolean clearCartForClient(Integer clientId) throws ECommerceServiceException {
        try {
            Optional<Client> client = clientRepository.findById(clientId);
            Optional<Cart> cartForCleint = cartRepository.findById(client.get().getCart().getId());
            List<Product> cartContents = cartForCleint.get().getCartContents();
            cartContents.clear();
            cartForCleint.get().setCartContents(cartContents);
            cartRepository.save(cartForCleint.get());
            return true;
        } catch (Exception exception) {
            throw new ECommerceServiceException("Exception while clearing cart for Client");
        }

    }

    public void add(Cart cart) {
        cartRepository.save(cart);
    }


    public Integer createCart() throws ECommerceServiceException {
        try {
            Cart cart = new Cart();
            Cart createdCart = cartRepository.save(cart);
            return createdCart.getId();
        } catch (Exception exception) {
            throw new ECommerceServiceException("Exception while trying to create cart..", exception);
        }

    }

    public Cart findItemsForClient(Integer clientId) throws ECommerceServiceException {
        try {
            return cartRepository.findByClientId(clientId);
        } catch (Exception exception) {
            throw new ECommerceServiceException("Exception while finding items in cart for client", exception);
        }

    }

    public Cart getCartForClient(Integer clientId) throws ECommerceServiceException {
        try {
            Optional<Client> client = clientRepository.findById(clientId);
            return cartRepository.findById(client.get().getCart().getId()).get();
        }catch (Exception exception){
            throw new ECommerceServiceException("Exception while retrieving cart for client", exception);
        }

    }

    public Cart addQuantityofProduct(Integer productId, Integer cartId) throws ECommerceServiceException {
        try{
            Optional<Product> product = productRepository.findById(productId);
            Optional<Cart> cart = cartRepository.findById(cartId);

            Map<Integer, Integer> quantityMap = cart.get().getProductQuantityMap();
            Integer quantity = cart.get().getProductQuantityMap().get(productId);
            quantity = ++quantity;
            quantityMap.put(productId, quantity);
            cart.get().setProductQuantityMap(quantityMap);
            cartRepository.save(cart.get());
            return cart.get();
        }catch (Exception exception){
            throw new ECommerceServiceException("Exception while adding quantity of product", exception);
        }

    }

}