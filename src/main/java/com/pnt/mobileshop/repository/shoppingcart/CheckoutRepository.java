package com.pnt.mobileshop.repository.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Long> {

    List<Checkout> findCheckoutsByUserId(Long id);

}
