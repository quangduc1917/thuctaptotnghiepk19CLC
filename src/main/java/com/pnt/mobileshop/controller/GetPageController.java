package com.pnt.mobileshop.controller;
import com.pnt.mobileshop.enity.Category;
import com.pnt.mobileshop.enity.Product;
import com.pnt.mobileshop.security.MyUserDetails;
import com.pnt.mobileshop.serviceImpl.CategoryServiceImpl;
import com.pnt.mobileshop.serviceImpl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GetPageController {


    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/")
    public String HomePage(Model model) {

        List<Category> listCategory = categoryServiceImpl.findAll();
        model.addAttribute("listCategory", listCategory);
        return "index";
    }

    @GetMapping("/aboutPage")
    public String AboutPage() {
        return "about";
    }

    @GetMapping("/comingPage")
    public String ComingPage() {
        return "coming";
    }

    @GetMapping("/contactPage")
    public String ContactPage() {
        return "contact";
    }

    @GetMapping("/loginPage")
    public String LoginPage() {
        return "login";
    }

    @GetMapping("/registerPage")
    public String RegisterPage() {
        return "register";
    }

    @GetMapping("/shopPage")
    public String ShopPage(Model model) {

        return findPaginated(1, model);
    }

    @GetMapping("/shopCategoryPage")
    public String ShopPagePaginatedCategory(Model model, @RequestParam(value = "category") String categoryName, @RequestParam(value = "pageNo") int pageNo) {

        return findPaginatedCategory(categoryName, pageNo, model);
    }

    @GetMapping("/singlePage")
    public String SinglePage() {
        return "single";
    }


    @GetMapping("/shopPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        int pageSize = 8;
        Page<Product> page = productServiceImpl.findPaginated(pageNo, pageSize);
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "shop";
    }


    @GetMapping("/shopCategoryPage/{category}/{pageNo}")
    public String findPaginatedCategory(@PathVariable(value = "category") String categoryName, @PathVariable(value = "pageNo") int pageNo, Model model) {

        int pageSize = 8;

        Page<Product> page = productServiceImpl.findProductByCategory_NamePaginated(categoryName,pageNo, pageSize);
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "shopCategory";
    }

    
}
