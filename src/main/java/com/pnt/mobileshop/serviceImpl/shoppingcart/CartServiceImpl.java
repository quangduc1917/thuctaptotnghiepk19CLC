package com.pnt.mobileshop.serviceImpl.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Cart;
import com.pnt.mobileshop.repository.shoppingcart.CartRepository;
import com.pnt.mobileshop.service.shoppingcart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void newCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findCartByUser_Id(Long id) {
        return cartRepository.findCartByUser_Id(id);
    }
}
