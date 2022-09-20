package com.pnt.mobileshop.service.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Checkout;

import java.util.List;

public interface CheckoutService {

    void saveCheckout(Checkout checkout);

    List<Checkout> findCheckoutsByUserId(Long id);

}
