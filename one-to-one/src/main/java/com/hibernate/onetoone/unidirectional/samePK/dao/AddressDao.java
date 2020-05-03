package com.hibernate.onetoone.unidirectional.samePK.dao;

import com.hibernate.onetoone.unidirectional.samePK.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<Address, Long> {
}
