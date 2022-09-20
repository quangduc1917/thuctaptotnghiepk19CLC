package com.pnt.mobileshop.service.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Cart;
import com.pnt.mobileshop.enity.shoppingcart.CartItem;

import java.util.List;

public interface CartItemService {

    void saveCartItem(CartItem cartItem);

    CartItem findCartItemByProduct_Id(Long id);

    List<CartItem> findItemsByCart_Id(Long id);

    void deleteCartItemById(Long id);

}
