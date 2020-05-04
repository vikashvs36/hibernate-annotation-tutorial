package com.hibernate.onetoone.samePK.dao;

import com.hibernate.onetoone.samePK.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
}
