package com.hibernate.onetoone;

import com.hibernate.onetoone.unidirectional.samePK.dao.UserDao;
import com.hibernate.onetoone.unidirectional.samePK.domain.Address;
import com.hibernate.onetoone.unidirectional.samePK.domain.User;
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

	@Override
	public void run(String... args) throws Exception {
		// Same primary key - Unidirectional
		samePkUnidirectional();
	}

	// Same primary key - Unidirectional
	private void samePkUnidirectional() {
		User user = new User("Vikash", "singh", new Address("Delhi"));
		userDao.save(user);
	}
}
