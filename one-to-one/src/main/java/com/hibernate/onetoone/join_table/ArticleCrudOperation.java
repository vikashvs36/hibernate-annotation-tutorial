package com.hibernate.onetoone.join_table;

import com.hibernate.onetoone.join_table.dao.ArticleDao;
import com.hibernate.onetoone.join_table.domain.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ArticleCrudOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCrudOperation.class);

    @Autowired
    private ArticleDao articleDao;

    public void crudOperation() {
        LOGGER.info("---------- Article :: crudOperation ----------");

        /*LOGGER.info("########## saveAritcle() ##########");
        // We can't operate save operation from owned side
        save();*/

        LOGGER.info("########## findAritcleById(4) ##########");
        findAritcleById(4);

        LOGGER.info("########## deleteAritcleById(4) ##########");
        delete(4);

        LOGGER.info("########## findAllAritcle() ##########");
        findAllAritcle();


    }

    private Article findAritcleById(long id) {
        Optional<Article> article = articleDao.findById(id);
        if (article.isPresent()) {
            LOGGER.info(article.get().toString());
            return article.get();
        }
        return null;
    }

    // We can't operate save operation from owned side
    private void save() {
        articleDao.save(new Article("title_6", "discreption_6", new Category("name_6")));
        articleDao.save(new Article("title_5", "discreption_5"));
    }

    private void delete(long id) {
        Article article = findAritcleById(id);
        if (article != null) {
            articleDao.delete(article);
        }
    }

    private void findAllAritcle() {
        List<Article> articleList = articleDao.findAll();
        articleList.forEach(obj-> LOGGER.info(obj.toString()));
    }

}
