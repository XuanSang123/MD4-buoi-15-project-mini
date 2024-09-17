package org.example.miniproject.services.book.impl;

import org.example.miniproject.dao.book.IBookDao;
import org.example.miniproject.dao.book.impl.BookDaoImpl;
import org.example.miniproject.models.Book;
import org.example.miniproject.services.book.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    private IBookDao bookDao = new BookDaoImpl();
    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void update(Book book) {
        bookDao.update(book);
    }

    @Override
    public void delete(Integer id) {
        bookDao.delete(id);
    }

    @Override
    public Book findById(Integer id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }
}
