package com.pnt.mobileshop.controller;

import com.pnt.mobileshop.enity.Category;
import com.pnt.mobileshop.enity.Product;
import com.pnt.mobileshop.enity.Role;
import com.pnt.mobileshop.enity.User;
import com.pnt.mobileshop.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final String UPLOAD_DIR = "/uploads/";

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    RoleServiceImpl roleServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    FileServiceImpl fileServiceImpl;

    @Autowired
    ProductServiceImpl productServiceImpl;

    @GetMapping("/productsPage")
    public String ProductsPage(Model model) {

        return findPaginated(1, model);
    }

    @GetMapping("/usersPage")
    public String UsersPage(Model model) {

        List<User> users = userServiceImpl.findAll();
        model.addAttribute("users", users);

        return "admin/users";

    }

    @GetMapping("/updateUserPage/{id}")

    public String UpdateUserPage(Model model,@PathVariable Long id ){
        User user = userServiceImpl.findUserById(id);
        model.addAttribute("userd", user);
        return "admin/formUpdateUser";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam("enabled") Boolean enabled, @RequestParam("roles") String name){

        User u = userServiceImpl.findUserById(id);
        Role r = roleServiceImpl.findRoleByName(name);
        Set<Role> set = new HashSet<>();
        set.add(r);
        u.setRoles(set);
        u.setEnabled(enabled);
        userServiceImpl.saveUser(u);
        return "redirect:/admin/usersPage";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable Long id){
        userServiceImpl.deleteUserById(id);
        return "redirect:/admin/usersPage";
    }

    @GetMapping("/productFormPage")
    public String ProductFormPage(Model model){

        List<Category> list = categoryServiceImpl.findAll();

        model.addAttribute("list", list);
        return "admin/formProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("file") MultipartFile multipartFile,
                             @RequestParam("name") String name,
                             @RequestParam("price") String price,
                             @RequestParam("des") String description,
                             @RequestParam("category") String category){
        String imageURL = UPLOAD_DIR + StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        fileServiceImpl.saveFile(multipartFile);

        double pric = Double.parseDouble(price);
        Category c = categoryServiceImpl.findCategoryByName(category);

        Product p = new Product();
        p.setName(name);
        p.setPrice(pric);
        p.setDescription(description);
        p.setImageUrl(imageURL);
        p.setCategory(c);
        productServiceImpl.saveProduct(p);

        return "redirect:/admin/productsPage";
    }

    @GetMapping("/updateProductPage/{id}")
    public String UpdateProductPage(Model model,@PathVariable Long id ){
        Product product = productServiceImpl.findProductById(id);
        Category category = product.getCategory();

        List<Category> list = categoryServiceImpl.findCategoryNotId(category.getId());

        model.addAttribute("list", list);
        model.addAttribute("product", product);
        return "admin/formUpdateProduct";
    }

    @PostMapping("/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam("file") MultipartFile multipartFile,
                                @RequestParam("name") String name,
                                @RequestParam("price") String price,
                                @RequestParam("des") String description,
                                @RequestParam("category") String category){

        String imageURL = UPLOAD_DIR + StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        fileServiceImpl.saveFile(multipartFile);

        double pric = Double.parseDouble(price);
        Category c = categoryServiceImpl.findCategoryByName(category);
        Product p = productServiceImpl.findProductById(id);
        p.setName(name);
        p.setPrice(pric);
        p.setDescription(description);
        if(!StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename())).equals("")){
            p.setImageUrl(imageURL);
        }
        p.setCategory(c);
        productServiceImpl.saveProduct(p);


        return "redirect:/admin/productsPage";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Long id){
        productServiceImpl.deleteProductById(id);
        return "redirect:/admin/productsPage";
    }

    @GetMapping("/productsPage/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

        int pageSize = 5;
        Page<Product> page = productServiceImpl.findPaginated(pageNo, pageSize);
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "admin/products";
    }



}
