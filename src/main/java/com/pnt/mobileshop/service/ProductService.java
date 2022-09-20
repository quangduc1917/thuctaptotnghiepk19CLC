package com.pnt.mobileshop.service;

import com.pnt.mobileshop.enity.Product;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    List<Product> findAllProducts();

    Product findProductById(Long id);

    void deleteProductById(Long id);

    Page<Product> findPaginated(int pageNo, int pageSize);

    Page<Product> findProductByCategory_NamePaginated(String categoryName, int pageNo, int pageSize);

}
