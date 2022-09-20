package com.pnt.mobileshop.controller.shoppingcart;

import com.pnt.mobileshop.enity.Product;
import com.pnt.mobileshop.enity.User;
import com.pnt.mobileshop.enity.shoppingcart.Cart;
import com.pnt.mobileshop.enity.shoppingcart.CartItem;
import com.pnt.mobileshop.enity.shoppingcart.Checkout;
import com.pnt.mobileshop.enity.shoppingcart.OrderItem;
import com.pnt.mobileshop.security.MyUserDetails;
import com.pnt.mobileshop.service.shoppingcart.OrderItemService;
import com.pnt.mobileshop.serviceImpl.UserServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartItemServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CheckoutServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.OrderItemServiceImpl;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @Autowired
    CartItemServiceImpl cartItemServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CheckoutServiceImpl checkoutServiceImpl;

    @Autowired
    OrderItemServiceImpl orderItemServiceImpl;

    @GetMapping("/checkoutPage")
    public String getCheckoutPage(Model model, @AuthenticationPrincipal MyUserDetails userDetails){

        Cart cart = cartServiceImpl.findCartByUser_Id(userDetails.getIdUser());
        List<CartItem> list = cartItemServiceImpl.findItemsByCart_Id(cart.getId());
        double totalPriceItems = 0;
        int totalItems = 0;
        int totalProduct = 0;
        for(CartItem ci : list){
            Product p = ci.getProduct();
            totalPriceItems += p.getPrice() * ci.getQuantity();
            totalItems += ci.getQuantity();
            totalProduct++;
        }

        model.addAttribute("list", list);
        model.addAttribute("totalPriceItems", totalPriceItems);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalProduct", totalProduct);

        return "checkout";
    }

    @PostMapping("/doneCheckout")
    public String doneCheckout(@RequestParam(name = "fullName") String fullName,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "phoneNumber") String phoneNumber,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "totalPrice") Double totalPrice,
                               @AuthenticationPrincipal MyUserDetails userDetails){

        LocalDateTime ldt = LocalDateTime.now ();
        ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
        Date date = Date.from(zdt.toInstant());

        User user = userServiceImpl.findUserById(userDetails.getIdUser());
        Cart cart = cartServiceImpl.findCartByUser_Id(userDetails.getIdUser());
        List<CartItem> list = cartItemServiceImpl.findItemsByCart_Id(cart.getId());
        for(CartItem cartItem : list){
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDate(date);
            orderItem.setUser(user);
            orderItemServiceImpl.saveOrderItem(orderItem);
        }
        List<OrderItem> listOrderItems = orderItemServiceImpl.findOrderItemsByDateAndUserId(date, user.getId());
        Checkout checkout = new Checkout();

        checkout.setFullName(fullName);
        checkout.setEmail(email);
        checkout.setListOrderItems(listOrderItems);
        checkout.setUser(user);
        checkout.setPhoneNumber(phoneNumber);
        checkout.setDescription(description);
        checkout.setDate(date);
        checkout.setAddress(address);
        checkout.setTotalPrice(totalPrice);

        checkoutServiceImpl.saveCheckout(checkout);

        return "redirect:/billPage";
    }
}
