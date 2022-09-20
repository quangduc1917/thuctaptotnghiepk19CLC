package com.pnt.mobileshop.repository.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findCartByUser_Id(Long id);

}
