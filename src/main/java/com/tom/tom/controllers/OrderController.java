package com.tom.tom.controllers;

import com.tom.tom.dto.CartOrderDTO;
import com.tom.tom.dto.ProductDTO;
import com.tom.tom.entities.Cart;
import com.tom.tom.entities.CartOrder;
import com.tom.tom.entities.Product;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.services.CartService;
import com.tom.tom.services.OrderService;
import com.tom.tom.services.ProductService;
import com.tom.tom.utilities.DTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findProductById(@PathVariable Integer id) throws ECommerceServiceException {
        Product product = productService.find(id);
        ProductDTO productDTO = DTOConverter.convertProductToDTO(product);
        return ResponseEntity.ok().body(productDTO);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllOrders() {
        List<CartOrder> orderList = cartService.findAll();
        List<CartOrderDTO> cartOrderDTOList = new ArrayList<>();
        for (CartOrder order :
                orderList) {
            CartOrderDTO cartOrderDTO = DTOConverter.convertToCartOrderToDTO(order);
            cartOrderDTOList.add(cartOrderDTO);
        }
        return ResponseEntity.ok().body(cartOrderDTOList);
    }

    @RequestMapping(value = "/placeOrder/{clientId}/{paymentMode}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> checkoutCart(@PathVariable Integer clientId, @PathVariable String paymentMode) throws ECommerceServiceException {
        Cart cart = cartService.getCartForClient(clientId);
        CartOrder order = null;
        if (!cart.getCartContents().isEmpty()) {
            order = orderService.order(cart, clientId, paymentMode);
            CartOrderDTO cartOrderDTO = DTOConverter.convertToCartOrderToDTO(order);
            return ResponseEntity.ok().body(cartOrderDTO);
        } else
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"no item added in cart to place order\"}");
    }
}
