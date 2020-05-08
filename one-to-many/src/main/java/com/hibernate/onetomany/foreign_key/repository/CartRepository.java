package com.hibernate.onetomany.foreign_key.repository;

import com.hibernate.onetomany.foreign_key.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> { }
