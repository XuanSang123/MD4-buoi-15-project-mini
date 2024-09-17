package org.example.miniproject.controller;

import org.example.miniproject.models.Book;
import org.example.miniproject.models.Category;
import org.example.miniproject.services.book.IBookService;
import org.example.miniproject.services.book.impl.BookServiceImpl;
import org.example.miniproject.services.category.ICategoryService;
import org.example.miniproject.services.category.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/books")
public class BookServlet extends HttpServlet {
    private IBookService bookService = new BookServiceImpl();
    private ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "create":
                    List<Category> categories = categoryService.findAll();
                    req.setAttribute("categories", categories);
                    req.getRequestDispatcher("/views/add-book.jsp").forward(req, res);
                    break;
                case "details":
                    // Xử lý hiển thị chi tiết book
                    Integer idDetails = Integer.valueOf(req.getParameter("id"));
                    req.setAttribute("book", bookService.findById(idDetails));
                    req.getRequestDispatcher("/views/details-book.jsp").forward(req, res);
                    break;
                case "update":
                    // Xử lý cập nhật book
                    Integer idEdit = Integer.valueOf(req.getParameter("id"));
                    req.setAttribute("book", bookService.findById(idEdit));
                    req.getRequestDispatcher("/views/edit-book.jsp").forward(req, res);
                    break;
                case "delete":
                    // Xử lý xóa book
                    Integer idDel = Integer.valueOf(req.getParameter("id"));
                    bookService.delete(idDel);
                    res.sendRedirect("/books?action=view");
                    break;
                case "list":
                    // Xử lý xem danh sách book
                    req.setAttribute("books", bookService.findAll());
                    req.getRequestDispatcher("/views/list-book.jsp").forward(req, res);
                    break;
                default:
                    // Xử lý trang chủ hoặc action không tồn tại
                    res.sendRedirect(req.getContextPath() + "/categories?action=view");
            }
        } else {
            // Nếu không có action thì mặc định về danh sách sách
            res.sendRedirect("/books?action=view");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    // Xử lý tạo mới book
                    Book addBook = new Book(
                            0,
                            new Category(Integer.parseInt(req.getParameter("categoryId"))),
                            req.getParameter("name"),
                            Double.parseDouble(req.getParameter("price")),
                            Integer.parseInt(req.getParameter("stock")),
                            Integer.parseInt(req.getParameter("totalPages")),
                            Integer.parseInt(req.getParameter("yearCreated")),
                            req.getParameter("author"),
                            Boolean.parseBoolean(req.getParameter("status"))
                    );
                    bookService.save(addBook);
                    res.sendRedirect("/books?action=view");
                    break;

                case "update":
                    // Xử lý cập nhật book
                    Book updateBook = new Book(
                            Integer.parseInt(req.getParameter("id")),
                            new Category(Integer.parseInt(req.getParameter("categoryId"))),
                            req.getParameter("name"),
                            Double.parseDouble(req.getParameter("price")),
                            Integer.parseInt(req.getParameter("stock")),
                            Integer.parseInt(req.getParameter("totalPages")),
                            Integer.parseInt(req.getParameter("yearCreated")),
                            req.getParameter("author"),
                            Boolean.parseBoolean(req.getParameter("status"))
                    );
                    bookService.update(updateBook);
                    res.sendRedirect("/books?action=view");
                    break;
            }
        }
    }
}
