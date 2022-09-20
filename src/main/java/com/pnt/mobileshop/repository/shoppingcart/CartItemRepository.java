package com.pnt.mobileshop.repository.shoppingcart;


import com.pnt.mobileshop.enity.shoppingcart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findCartItemByProduct_Id(Long id);

    List<CartItem> findCartItemsByCart_Id(Long id);

    void deleteById(Long id);

}
