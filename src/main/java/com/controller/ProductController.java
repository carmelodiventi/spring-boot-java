package com.controller;

import com.entity.Orders;
import com.entity.Product;
import com.entity.User;
import com.service.OrdersService;
import com.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OrdersService ordersService;

    @PostMapping("/add")
    public String addProduct(Product product, RedirectAttributes redirectAttributes) { // RedirectAttributes is used to pass data to the redirected page
        String result = productService.storeProduct(product);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/home";
    }

    @PostMapping("/update/{pid}")
    public String updateProduct(@PathVariable("pid") int pid, Product product, RedirectAttributes redirectAttributes) {
        String result = productService.updateProduct(pid, product);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/home";
    }

    @GetMapping("/details/{pid}")
    public String detailsProduct(@PathVariable("pid") int pid, Model model, HttpSession session) {
        Product product = productService.getProduct(pid);
        model.addAttribute("product", product);
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        return "product";
    }

    @GetMapping("/buy/{pid}/{uid}")
    public String buyProduct(@PathVariable("pid") int pid, @PathVariable("uid") int uid, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String result = ordersService.storeOrder(pid, uid);
        model.addAttribute("user", session.getAttribute("loggedInUser"));
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/orders/list";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("pid") int pid, Model model, RedirectAttributes redirectAttributes) {
        String result = productService.deleteProduct(pid);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/home";
    }

}
