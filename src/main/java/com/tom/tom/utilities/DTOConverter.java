package com.tom.tom.utilities;

import com.tom.tom.dto.CartDTO;
import com.tom.tom.dto.CartOrderDTO;
import com.tom.tom.entities.Cart;
import com.tom.tom.entities.CartOrder;
import com.tom.tom.entities.Product;
import com.tom.tom.dto.ProductDTO;
import com.tom.tom.dto.SellerDTO;
import com.tom.tom.entities.Seller;
import java.util.ArrayList;
import java.util.List;

public class DTOConverter {

    /**
     * Method to convert Product entity to ProductDTO
     *
     * @param product
     * @return
     */
    public static ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCategory(product.getCategory());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());

        SellerDTO sellerDTO = converTOtSellerDTO(product.getSeller());

        productDTO.setSeller(sellerDTO);
        return productDTO;
    }

    /**
     * Method to convert Product entity to ProductDTO
     *
     * @param productDTO
     * @return
     */
    public static Product convertDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setCategory(product.getCategory());
        product.setName(product.getName());
        product.setPrice(product.getPrice());

        Seller seller = converDTOToSeller(productDTO.getSeller());

        product.setSeller(seller);
        return product;
    }

    /**
     * Method to convert DTO to Seller
     *
     * @param sellerDTO
     * @return
     */
    static Seller converDTOToSeller(SellerDTO sellerDTO) {
        Seller seller = new Seller();
        seller.setName(sellerDTO.getName());
        seller.setEmail(sellerDTO.getEmail());
        seller.setAddress(sellerDTO.getAddress());
        return seller;
    }

    /**
     * Method to convert Seller Entity to Seller DTO
     *
     * @param seller
     * @return
     */
    public static SellerDTO converTOtSellerDTO(Seller seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setName(seller.getName());
        sellerDTO.setAddress(seller.getAddress());
        sellerDTO.setEmail(seller.getEmail());
        return sellerDTO;
    }

    /**
     * Method to convert DTO to cartOrder object
     *
     * @param cartOrderDTO
     * @return
     */
    public static CartOrder convertDTOtoCartOrder(CartOrderDTO cartOrderDTO) {
        CartOrder cartOrder = new CartOrder();
        cartOrder.setPaymentStatus(cartOrderDTO.getPaymentStatus());
        cartOrder.setClientId(cartOrderDTO.getClientId());
        cartOrder.setDeliveryDate(cartOrderDTO.getDeliveryDate());
        cartOrder.setOrderStatus(cartOrderDTO.getOrderStatus());
        cartOrder.setOrderTotalValue(cartOrderDTO.getOrderTotalValue());
        cartOrder.setPaymentMode(cartOrderDTO.getPaymentMode());
        return cartOrder;

    }

    /**
     * Method to convert CartOrder to DTO
     *
     * @param cartOrder
     * @return
     */
    public static CartOrderDTO convertToCartOrderToDTO(CartOrder cartOrder) {
        CartOrderDTO cartOrderDTO = new CartOrderDTO();
        cartOrderDTO.setPaymentStatus(cartOrder.getPaymentStatus());
        cartOrderDTO.setClientId(cartOrder.getClientId());
        cartOrderDTO.setDeliveryDate(cartOrder.getDeliveryDate());
        cartOrderDTO.setOrderStatus(cartOrder.getOrderStatus());
        cartOrderDTO.setOrderTotalValue(cartOrder.getOrderTotalValue());
        cartOrderDTO.setPaymentMode(cartOrder.getPaymentMode());
        return cartOrderDTO;
    }


    /**
     * Method to convert DTO to cart object
     *
     * @param cartDTO
     * @return
     */
    public static Cart convertDTOToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setProductQuantityMap(cartDTO.getProductQuantityMap());
        List<Product> productList = new ArrayList<>();
        for (ProductDTO productDTO :
                cartDTO.getCartContents()) {
            Product product = DTOConverter.convertDTOToProduct(productDTO);
            productList.add(product);
        }
        cart.setCartContents(productList);
        return cart;

    }

    /**
     * Method to convert Cart to cart DTO object
     *
     * @param cart
     * @return
     */
    public static CartDTO converCartToDTO(Cart cart) {
        CartDTO cartDTOrt = new CartDTO();
        cartDTOrt.setProductQuantityMap(cart.getProductQuantityMap());
        List<ProductDTO> productList = new ArrayList<>();
        for (Product product :
                cart.getCartContents()) {
            ProductDTO productDTO = DTOConverter.convertProductToDTO(product);
            productList.add(productDTO);
        }
        cartDTOrt.setCartContents(productList);
        return cartDTOrt;
    }
}
