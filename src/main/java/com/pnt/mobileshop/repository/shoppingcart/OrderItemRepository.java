package com.pnt.mobileshop.repository.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findOrderItemsByDateAndUserId(Date date, Long id);

}
