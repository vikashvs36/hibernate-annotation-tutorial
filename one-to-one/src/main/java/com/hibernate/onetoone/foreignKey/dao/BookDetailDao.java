package com.hibernate.onetoone.foreignKey.dao;

import com.hibernate.onetoone.foreignKey.domain.BookDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailDao extends JpaRepository<BookDetail, Long> {
}
