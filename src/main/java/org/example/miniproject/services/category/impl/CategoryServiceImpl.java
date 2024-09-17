package org.example.miniproject.services.category.impl;

import org.example.miniproject.dao.category.ICategoryDao;
import org.example.miniproject.dao.category.impl.CategoryDaoImpl;
import org.example.miniproject.models.Category;
import org.example.miniproject.services.category.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryDao.delete(id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
