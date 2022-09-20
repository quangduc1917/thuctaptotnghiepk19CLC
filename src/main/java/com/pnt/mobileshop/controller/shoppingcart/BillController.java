package com.pnt.mobileshop.controller.shoppingcart;

import com.pnt.mobileshop.enity.shoppingcart.Checkout;
import com.pnt.mobileshop.security.MyUserDetails;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CheckoutServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BillController {

    @Autowired
    CheckoutServiceImpl checkoutServiceImpl;

    @GetMapping("/billPage")
    public String getBillPage(@AuthenticationPrincipal MyUserDetails userDetails, Model model){

        List<Checkout> list = checkoutServiceImpl.findCheckoutsByUserId(userDetails.getIdUser());
        model.addAttribute("list", list);

        return "bill";
    }

}
