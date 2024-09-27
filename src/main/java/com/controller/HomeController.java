package com.controller;

import com.entity.Product;
import com.entity.User;
import com.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/home")
    public String home(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "productPrice", required = false) Float productPrice,
            Product product, Model model,
            HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) return "redirect:/";

        List<Product> listOfProducts;

        if ((productName != null && !productName.isEmpty()) || (productPrice != null && productPrice > 0)) {
            listOfProducts = productService.filterProducts(productName, productPrice);
            model.addAttribute("productName", productName);
            model.addAttribute("productPrice", productPrice);
        } else {
            listOfProducts = productService.getAllProducts();
        }

        model.addAttribute("product", product);
        model.addAttribute("products", listOfProducts);
        model.addAttribute("user", loggedInUser);

        return "home";
    }

}
