package com.hibernate.onetoone.samePK;

import com.hibernate.onetoone.samePK.dao.UserDao;
import com.hibernate.onetoone.samePK.domain.Address;
import com.hibernate.onetoone.samePK.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserSamePkCrudOperation {

    @Autowired
    private UserDao userDao;

    public void crudOperation() {
        System.out.println("------------ USER :: crudOperation ------------");

        // Save User object
        System.out.println("############ :: saveAll :: ############");
        saveAll();

        // Find User by Id
        System.out.println("############ :: findUserById :: ############");
        findUserById(1);

        // delete User
        System.out.println("############ :: delete :: ############");
        delete(1);

        // FindAll Users.
        System.out.println("############ :: findAll :: ############");
        findAll();

    }

    // Save multiple user and address.
    private void saveAll() {
        List<User> createUsers = Arrays.asList(
                new User("Vikash", "singh", new Address("Delhi")),
                new User("Anil", "gupta", new Address("Noida"))
        );
        // Save multiple objects.
        userDao.saveAll(createUsers);
    }

    // Find User by id
    private User findUserById(long id) {
        Optional<User> optional = userDao.findById(id);
        User user = optional.isPresent() ? optional.get() : null;
        System.out.println("User : "+user+", Address : "+user.getAddress());
        return user;
    }

    // Delete User if exist
    private void delete(long id) {
        User user = findUserById(id);
        if(user != null) {
            userDao.delete(user);
        }
    }

    // FindAll Users.
    private void findAll() {
        List<User> users = userDao.findAll();
        users.forEach(user -> System.out.println("User : "+user+", Address : "+user.getAddress()));
    }

}
