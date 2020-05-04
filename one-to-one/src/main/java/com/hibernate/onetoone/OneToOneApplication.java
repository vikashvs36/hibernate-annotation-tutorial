package com.hibernate.onetoone;

import com.hibernate.onetoone.samePK.UserSamePkCrudOperation;
import com.hibernate.onetoone.samePK.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneToOneApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OneToOneApplication.class, args);
	}

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSamePkCrudOperation userCrudOperation;

	@Override
	public void run(String... args) throws Exception {

		// Same primary key - Unidirectional
		userCrudOperation.crudOperation();



	}

}
