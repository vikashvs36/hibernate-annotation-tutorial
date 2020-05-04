package com.hibernate.onetoone;

import com.hibernate.onetoone.samePK.AddressSamePkCrudOperation;
import com.hibernate.onetoone.samePK.UserSamePkCrudOperation;
import com.hibernate.onetoone.samePK.dao.UserDao;
import com.hibernate.onetoone.samePK.domain.Address;
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
	private UserSamePkCrudOperation userCrudOperation;

	@Autowired
	private AddressSamePkCrudOperation addressSamePkCrudOperation;

	@Override
	public void run(String... args) throws Exception {

		// Same primary key - Unidirectional
		userCrudOperation.crudOperation();

		// Same primary key - Bidirectional
		addressSamePkCrudOperation.crudOperation();

	}

}
