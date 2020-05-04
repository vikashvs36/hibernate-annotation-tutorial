package com.hibernate.onetoone.samePK;

import com.hibernate.onetoone.samePK.dao.AddressDao;
import com.hibernate.onetoone.samePK.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressSamePkCrudOperation {
    
    @Autowired
    private AddressDao addressDao;

    public void crudOperation() {
        System.out.println("------------ Address :: crudOperation ------------");
        // Find User by Id
        System.out.println("############ :: findUserById :: ############");
        findUserById(2);

    }

    // Find User by id
    private Address findUserById(long id) {
        Optional<Address> optional = addressDao.findById(id);
        Address address = optional.isPresent() ? optional.get() : null;
        System.out.println("Address : "+address+", User : "+address.getUser());
        return address;
    }

}
