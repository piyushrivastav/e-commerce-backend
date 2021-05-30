package com.tom.tom.services;

import com.tom.tom.entities.Cart;
import com.tom.tom.entities.CartOrder;
import com.tom.tom.entities.Product;
import com.tom.tom.exceptions.ECommerceServiceException;
import com.tom.tom.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public CartOrder order(Cart cart, Integer clientId, String paymentMode) throws ECommerceServiceException {
        try{
            CartOrder order = new CartOrder();

            order.setClientId(clientId);
            Double totalOrderValue = calculateTotalValue(cart);
            order.setOrderTotalValue(totalOrderValue);
            order.setDeliveryDate("20/05/2021"); //add logic to ask seller for due delivery date

            switch(paymentMode) {
                case "COD":
                    order.setPaymentStatus("PENDING");
                    break;
                default:
                    //add call to payment gateway (internet banking/UPI/debit cart)
                    order.setPaymentStatus("PAID");
            }
            order.setOrderStatus("PLACED");
            order.setPaymentMode(paymentMode);
            orderRepository.save(order);
            return order;
        }catch (Exception exception){
            throw new ECommerceServiceException("Exception while placing order", exception);
        }

    }

    private Double calculateTotalValue(Cart cart) throws ECommerceServiceException {
        try {
            List<Product> productList = cart.getCartContents();
            Map<Integer, Integer> prodQuantityMap = cart.getProductQuantityMap();
            Double sum = 0.0;
            for (Product product :
                    productList) {
                sum = sum + product.getPrice() * prodQuantityMap.get(product.getId());
            }
            return sum;
        }catch (Exception exception){
            throw new ECommerceServiceException("Exception while calculating total value of products in cart", exception);
        }

    }
}