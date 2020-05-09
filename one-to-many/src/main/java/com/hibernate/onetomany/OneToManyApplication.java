package com.hibernate.onetomany;

import com.hibernate.onetomany.foreign_key.CartForeignKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToManyApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(OneToManyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OneToManyApplication.class, args);
	}

	@Autowired
	private CartForeignKeyService cartForeignKeyService;

	@Override
	public void run(String... args) throws Exception {

//		LOGGER.info("----------------- CartForeignKeyService :: CrudOperation -----------------");
//		cartService();


	}

	private void cartService() {
		LOGGER.info("############ saveAllCart()  ############");
		cartForeignKeyService.saveCart();

		LOGGER.info("############ findCartById(1)  ############");
		cartForeignKeyService.findById(1);

		LOGGER.info("############ deleteCartById(1)  ############");
		cartForeignKeyService.delete(1);

		LOGGER.info("############ findAllCart()  ############");
		cartForeignKeyService.findAll();
	}
}
