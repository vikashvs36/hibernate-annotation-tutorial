package com.hibernate.onetoone.foreignKey.dao;

import com.hibernate.onetoone.foreignKey.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, Long> { }
