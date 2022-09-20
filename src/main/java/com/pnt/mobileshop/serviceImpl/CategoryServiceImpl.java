package com.pnt.mobileshop.serviceImpl;

import com.pnt.mobileshop.enity.Category;
import com.pnt.mobileshop.repository.CategoryRepository;
import com.pnt.mobileshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findCategoryNotId(Long id) {
        return categoryRepository.findCategoryNotId(id);
    }


}
