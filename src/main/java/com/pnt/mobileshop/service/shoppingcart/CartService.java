package com.pnt.mobileshop.service.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Cart;

public interface CartService {

    void newCart(Cart cart);

    Cart findCartByUser_Id(Long id);

}
