package com.pnt.mobileshop.serviceImpl.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.OrderItem;
import com.pnt.mobileshop.repository.shoppingcart.OrderItemRepository;
import com.pnt.mobileshop.service.shoppingcart.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findOrderItemsByDateAndUserId(Date date, Long id) {
        return orderItemRepository.findOrderItemsByDateAndUserId(date, id);
    }
}
