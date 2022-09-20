package com.pnt.mobileshop.repository;

import com.pnt.mobileshop.enity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);

    @Query(value = "select * from category where category_id != ?1", nativeQuery = true)
    List<Category> findCategoryNotId(Long id);
}
