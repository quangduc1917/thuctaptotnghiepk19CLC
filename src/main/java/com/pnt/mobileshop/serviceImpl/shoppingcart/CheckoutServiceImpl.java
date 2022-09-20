package com.pnt.mobileshop.serviceImpl.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Checkout;
import com.pnt.mobileshop.repository.shoppingcart.CheckoutRepository;
import com.pnt.mobileshop.service.shoppingcart.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    CheckoutRepository checkoutRepository;

    @Override
    public void saveCheckout(Checkout checkout) {
        checkoutRepository.save(checkout);
    }

    @Override
    public List<Checkout> findCheckoutsByUserId(Long id) {
        return checkoutRepository.findCheckoutsByUserId(id);
    }
}
