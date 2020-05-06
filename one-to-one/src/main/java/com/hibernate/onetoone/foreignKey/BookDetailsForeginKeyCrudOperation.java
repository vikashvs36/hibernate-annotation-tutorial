package com.hibernate.onetoone.foreignKey;

import com.hibernate.onetoone.foreignKey.dao.BookDetailDao;
import com.hibernate.onetoone.foreignKey.domain.BookDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookDetailsForeginKeyCrudOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookDetailsForeginKeyCrudOperation.class);

    @Autowired
    private BookDetailDao bookDetailDao;

    public void crudOperation() {
        LOGGER.info("########## BookDetails :: crudOperation() ##########");

        LOGGER.info("########## FindById(1) ##########");
        Optional<BookDetail> bookDetail = bookDetailDao.findById(1L);
        if (bookDetail.isPresent()) {
            LOGGER.info("{}, {}", bookDetail.get(), bookDetail.get().getBook());
        }
    }

}
