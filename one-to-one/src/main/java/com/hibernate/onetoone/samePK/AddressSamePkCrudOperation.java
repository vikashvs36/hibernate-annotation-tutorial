package com.hibernate.onetoone.samePK;

import com.hibernate.onetoone.samePK.dao.AddressDao;
import com.hibernate.onetoone.samePK.domain.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddressSamePkCrudOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressSamePkCrudOperation.class);

    @Autowired
    private AddressDao addressDao;

    public void crudOperation() {
        LOGGER.info("------------ Address :: crudOperation ------------");
        // Find User by Id
        LOGGER.info("############ :: findUserById :: ############");
        findUserById(2);

    }

    // Find User by id
    private Address findUserById(long id) {
        Optional<Address> optional = addressDao.findById(id);
        Address address = optional.isPresent() ? optional.get() : null;
        LOGGER.info("Address : {}, User : {}", address, address.getUser());
        return address;
    }

}
