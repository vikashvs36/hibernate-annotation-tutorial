package com.hibernate.onetoone.foreignKey;

import com.hibernate.onetoone.foreignKey.dao.BookDao;
import com.hibernate.onetoone.foreignKey.domain.Book;
import com.hibernate.onetoone.foreignKey.domain.BookDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookForeignKeyCrudOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookForeignKeyCrudOperation.class);

    @Autowired
    private BookDao bookDao;

    public void crudOperation() {
        LOGGER.info("########### Book :: Crud Operation ###########");

        LOGGER.info("########### SaveAll() ###########");
        save();

        LOGGER.info("########### FindBookById() ###########");
        findBookById(1);

        LOGGER.info("########### delete() ###########");
        delete(2);

        LOGGER.info("########### FindAll() ###########");
        findAll();
    }

    private Book findBookById(long id) {
        Optional<Book> book = bookDao.findById(id);
        if(book.isPresent()) {
            System.out.println(book.get());
            return book.get();
        }
        return null;
    }

    private void findAll() {
        List<Book> bookList = bookDao.findAll();
        bookList.forEach(obj-> LOGGER.info(obj.toString()));
    }

    private void save() {
        List<Book> bookList = Arrays.asList(
                new Book("SCJP", new BookDetail(870)),
                new Book("K.C.SINHA", new BookDetail(435)),
                new Book("STUDENT FRIENDS", new BookDetail(435))
        );
        bookDao.saveAll(bookList);
    }

    private void delete(long id) {
        Book book = findBookById(id);
        if (book != null) {
            bookDao.delete(book);
        }
    }
}
