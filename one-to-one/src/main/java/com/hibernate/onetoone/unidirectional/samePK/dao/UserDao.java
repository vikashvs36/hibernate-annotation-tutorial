package com.hibernate.onetoone.unidirectional.samePK.dao;

import com.hibernate.onetoone.unidirectional.samePK.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
