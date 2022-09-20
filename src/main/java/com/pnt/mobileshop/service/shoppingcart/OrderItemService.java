package com.pnt.mobileshop.service.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.OrderItem;

import java.util.Date;
import java.util.List;

public interface OrderItemService {

    void saveOrderItem(OrderItem orderItem);

    List<OrderItem> findOrderItemsByDateAndUserId(Date date, Long id);

}
