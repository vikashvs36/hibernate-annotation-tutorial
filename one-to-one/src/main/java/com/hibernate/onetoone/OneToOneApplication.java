package com.hibernate.onetoone;

import com.hibernate.onetoone.foreignKey.BookDetailsForeginKeyCrudOperation;
import com.hibernate.onetoone.foreignKey.BookForeignKeyCrudOperation;
import com.hibernate.onetoone.join_table.CategoryCrudOperation;
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

	@Autowired
	private BookForeignKeyCrudOperation bookForeignKeyCrudOperation;

	@Autowired
	private BookDetailsForeginKeyCrudOperation bookDetailsForeginKeyCrudOperation;

	@Autowired
	private CategoryCrudOperation categoryOperation;

	@Override
	public void run(String... args) throws Exception {

		// Same primary key - Unidirectional
//		userCrudOperation.crudOperation();

		// Same primary key - Bidirectional
//		addressSamePkCrudOperation.crudOperation();

		// Foreign key - Unidirectional
//		bookForeignKeyCrudOperation.crudOperation();

		// Foreign key - Bidirectional
//		bookDetailsForeginKeyCrudOperation.crudOperation();

		// Join Table - Unidirectional
		categoryOperation.crudOperation();


	}

}
