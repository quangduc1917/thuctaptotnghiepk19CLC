package com.pnt.mobileshop.controller.shoppingcart;

import com.pnt.mobileshop.enity.Product;
import com.pnt.mobileshop.enity.shoppingcart.Cart;
import com.pnt.mobileshop.enity.shoppingcart.CartItem;
import com.pnt.mobileshop.security.MyUserDetails;
import com.pnt.mobileshop.serviceImpl.ProductServiceImpl;
import com.pnt.mobileshop.serviceImpl.UserServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartItemServiceImpl;
import com.pnt.mobileshop.serviceImpl.shoppingcart.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shoppingCart")
public class CartController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    CartServiceImpl cartServiceImpl;

    @Autowired
    CartItemServiceImpl cartItemServiceImpl;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(value = "id") Long product_id, @AuthenticationPrincipal MyUserDetails userDetails){

        Product product = productServiceImpl.findProductById(product_id);
        Cart cart = cartServiceImpl.findCartByUser_Id(userDetails.getIdUser());

        try{
            CartItem cartItemExist = cartItemServiceImpl.findCartItemByProduct_Id(product.getId());
            if(cartItemExist != null){
                int quantity = cartItemExist.getQuantity() + 1;
                cartItemExist.setQuantity(quantity);
                cartItemServiceImpl.saveCartItem(cartItemExist);
                return "redirect:/cartPage";
            }

        } catch (Exception ex){
            System.out.println(ex);
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setCart(cart);
        cartItemServiceImpl.saveCartItem(cartItem);

        return "redirect:/cartPage";
    }

    @GetMapping("/deleteCartItem/{id}")
    public String deleteCartItem(@PathVariable(value = "id") Long id){
        cartItemServiceImpl.deleteCartItemById(id);

        return "redirect:/cartPage";
    }

}
