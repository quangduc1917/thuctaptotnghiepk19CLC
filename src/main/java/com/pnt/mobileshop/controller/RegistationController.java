package com.pnt.mobileshop.controller;

import com.pnt.mobileshop.enity.Role;
import com.pnt.mobileshop.enity.User;
import com.pnt.mobileshop.enity.shoppingcart.Cart;
import com.pnt.mobileshop.serviceImpl.RoleServiceImpl;
import com.pnt.mobileshop.serviceImpl.UserServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;


@Controller
public class RegistationController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CartServiceImpl cartServiceImpl;

    @PostMapping("/register")
    public String registation(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirmpassword") String confirmpassword, Model model){

        if(!password.equals(confirmpassword)){
            return "redirect:/registerPage?error";
        }
        if(userServiceImpl.findUserByUsername(username) != null){
            return "redirect:/registerPage?existed";
        }

        Role r = roleServiceImpl.findRoleByName("USER");

        Set<Role> set = new HashSet<>();
        set.add(r);

            User u = new User();
            u.setUsername(username);
            u.setPassword(passwordEncoder.encode(password));
            u.setEnabled(true);
            u.setRoles(set);
            userServiceImpl.saveUser(u);

        Cart cart = new Cart();
        cart.setUser(u);
        cartServiceImpl.newCart(cart);

        return "redirect:/loginPage?success";
    }
}
