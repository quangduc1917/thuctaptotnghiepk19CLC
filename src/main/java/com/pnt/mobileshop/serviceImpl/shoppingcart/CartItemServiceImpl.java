package com.pnt.mobileshop.serviceImpl.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.CartItem;
import com.pnt.mobileshop.repository.shoppingcart.CartItemRepository;
import com.pnt.mobileshop.service.shoppingcart.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem findCartItemByProduct_Id(Long id) {
        return cartItemRepository.findCartItemByProduct_Id(id);
    }

    @Override
    public List<CartItem> findItemsByCart_Id(Long id) {
        return cartItemRepository.findCartItemsByCart_Id(id);
    }

    @Override
    public void deleteCartItemById(Long id) {
        cartItemRepository.deleteById(id);
    }

}
