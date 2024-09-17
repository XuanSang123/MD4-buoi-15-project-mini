package org.example.miniproject.dao.book.impl;

import org.example.miniproject.dao.book.IBookDao;
import org.example.miniproject.dao.category.ICategoryDao;
import org.example.miniproject.models.Book;
import org.example.miniproject.models.Category;
import org.example.miniproject.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {
    private ICategoryDao categoryDao; // Giả sử bạn có CategoryDao để tìm Category

    @Override
    public void save(Book book) {
        Connection connection = DBConnection.getConnection();
        CallableStatement sta = null;
        try{
            sta = connection.prepareCall("insert into book (name, price, stock, totalPages, yearCreated, author, status, categoryId)");
            sta.setString(1, book.getName());
            sta.setDouble(2, book.getPrice());
            sta.setInt(3, book.getStock());
            sta.setInt(4, book.getTotalPages());
            sta.setInt(5, book.getYearCreated());
            sta.setString(6, book.getAuthor());
            sta.setBoolean(7, book.isStatus());
            sta.setInt(8, book.getCategoryId().getId());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public void update(Book book) {
        Connection connection = DBConnection.getConnection();
        CallableStatement sta = null;
        try{
            sta = connection.prepareCall("update book set name =?, price =?, stock =?, totalPages =?, yearCreated =?, author =?, status =?, categoryId =? where id =?");
            sta.setString(1, book.getName());
            sta.setDouble(2, book.getPrice());
            sta.setInt(3, book.getStock());
            sta.setInt(4, book.getTotalPages());
            sta.setInt(5, book.getYearCreated());
            sta.setString(6, book.getAuthor());
            sta.setBoolean(7, book.isStatus());
            sta.setInt(8, book.getCategoryId().getId());
            sta.setInt(9, book.getId());
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection connection = DBConnection.getConnection();
        CallableStatement sta = null;
        try{
            sta = connection.prepareCall("delete from book where id = ?");
            sta.setInt(1, id);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public Book findById(Integer id) {
       Connection connection = DBConnection.getConnection();
       CallableStatement sta = null;
        try {
            sta = connection.prepareCall("select * from book where id = ?");
            sta.setInt(1, id);
           ResultSet rs  =sta.executeQuery();
            if (rs.next()) {
                Category category = categoryDao.findById(rs.getInt("categoryId"));
                return new Book(
                        rs.getInt("id"),
                        category,
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getInt("totalPages"),
                        rs.getInt("yearCreated"),
                        rs.getString("author"),
                        rs.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        CallableStatement sta = null;
        try {
            sta = connection.prepareCall("select * from book");
            ResultSet rs  = sta.executeQuery();
            while (rs.next()) {
                Category category = categoryDao.findById(rs.getInt("categoryId"));
                Book book = new Book(
                        rs.getInt("id"),
                        category,
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getInt("totalPages"),
                        rs.getInt("yearCreated"),
                        rs.getString("author"),
                        rs.getBoolean("status")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
