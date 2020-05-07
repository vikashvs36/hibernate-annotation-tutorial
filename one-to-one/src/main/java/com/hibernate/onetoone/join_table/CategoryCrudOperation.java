package com.hibernate.onetoone.join_table;

import com.hibernate.onetoone.join_table.dao.CategoryDao;
import com.hibernate.onetoone.join_table.domain.Article;
import com.hibernate.onetoone.join_table.domain.Category;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CategoryCrudOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryCrudOperation.class);

    @Autowired
    private CategoryDao categoryDao;

    public void crudOperation() {
        LOGGER.info("---------- Category :: crudOperation ----------");

        LOGGER.info("########## saveAll() ##########");
        saveAll();

        LOGGER.info("########## findCategoryById(1) ##########");
        findCategoryById(1);

        LOGGER.info("########## deleteCategory(1) ##########");
        deleteCategory(1);

        LOGGER.info("########## findAllCategory() ##########");
        findAllCategory();
    }

    private void saveAll() {
        List<Category> categoryList = Arrays.asList(
                new Category("name_1", new Article("title_1","discreption_1")),
                new Category("name_2", new Article("title_2","discreption_2")),
                new Category("name_3", new Article("title_3","discreption_3")),
                new Category("name_4", new Article("title_4","discreption_4"))
        );
        categoryDao.saveAll(categoryList);
    }

    private Category findCategoryById(long id) {
        Optional<Category> category = categoryDao.findById(id);
        if (category.isPresent()) {
            LOGGER.info(category.get().toString());
            return category.get();
        }
        return null;
    }

    private void deleteCategory(long id) {
        Category category = findCategoryById(id);
        if (category != null) {
            categoryDao.delete(category);
        }
    }

    private void findAllCategory() {
        List<Category> categoryList = categoryDao.findAll();
        categoryList.forEach(obj -> LOGGER.info(obj.toString()));
    }

}
