package com.tom.tom;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.tom.tom.entities.*;
import com.tom.tom.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication implements CommandLineRunner {


	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private SellerRepository sellerRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product p1 = new Product(null, "Computer", 1800.00, "electronics");
		Product p2 = new Product(null, "MacBook", 2800.00, "electronics");
		Product p3 = new Product(null, "Ipad", 400.00, "electronics");




		Client cli1 = new Client(null, "Mike Smith", "msmith@gmail.com", "63789300");
		Cart cart = new Cart(null, 1);
		Seller seller = new Seller(null, "seller1", "seller@seller.com");
		seller.setAddress("haha");
		p1.setSeller(seller);
		p2.setSeller(seller);
		p3.setSeller(seller);
		cli1.setCart(cart);
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		cli1.getPhones().addAll(Arrays.asList("536383839", "9202938930"));


		clientRepository.saveAll(Arrays.asList(cli1));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	}
}
