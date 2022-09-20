package com.pnt.mobileshop.controller.shoppingcart;

import com.pnt.mobileshop.enity.Product;
import com.pnt.mobileshop.enity.shoppingcart.Cart;
import com.pnt.mobileshop.enity.shoppingcart.CartItem;
import com.pnt.mobileshop.security.MyUserDetails;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartItemServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetCartPageController {

    @Autowired
    CartServiceImpl cartServiceImpl;

    @Autowired
    CartItemServiceImpl cartItemServiceImpl;

    @GetMapping("/cartPage")
    public String cartPage(Model model, @AuthenticationPrincipal MyUserDetails userDetails){

        Cart cart = cartServiceImpl.findCartByUser_Id(userDetails.getIdUser());
        List<CartItem> list = cartItemServiceImpl.findItemsByCart_Id(cart.getId());

        double totalPriceItems = 0;
        int totalItems = 0;
        for(CartItem ci : list){
            Product p = ci.getProduct();
            totalPriceItems += p.getPrice() * ci.getQuantity();
            totalItems += ci.getQuantity();
        }

        model.addAttribute("list", list);
        model.addAttribute("totalPriceItems", totalPriceItems);
        model.addAttribute("totalItems", totalItems);

        return "cart";
    }

}
