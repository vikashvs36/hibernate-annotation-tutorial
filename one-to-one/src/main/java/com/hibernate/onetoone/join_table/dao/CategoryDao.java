package com.hibernate.onetoone.join_table.dao;

import com.hibernate.onetoone.join_table.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> { }
