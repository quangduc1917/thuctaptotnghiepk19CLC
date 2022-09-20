package com.pnt.mobileshop.repository;

import com.pnt.mobileshop.enity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);

    void deleteById(Long id);

    @Query(value = "select products.* from products , category  where products.category_id = category.category_id and UPPER(category.name) LIKE CONCAT('%',UPPER(?1),'%')", nativeQuery = true)
    Page<Product> findProductByCategory_NamePaginated(String categoryName, org.springframework.data.domain.Pageable pageable);



}
