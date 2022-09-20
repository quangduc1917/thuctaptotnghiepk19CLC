package com.pnt.mobileshop.service;

import com.pnt.mobileshop.enity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findCategoryByName(String name);

    List<Category> findCategoryNotId(Long id);

}
