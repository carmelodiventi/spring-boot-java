package com.controller;

import com.entity.Orders;
import com.entity.User;
import com.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/orders")
@Controller
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/list")
    public String ordersList(Model model, HttpSession session) {
        List<Orders> orders;

        if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/";
        }

        User user = (User) session.getAttribute("loggedInUser");

        if(user.getRole().contains("admin")){
            orders = ordersService.getAllOrders();
        } else {
            orders = ordersService.getOrdersByUid(user.getUid());
        }

        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/delete")
    public String deleteOrder(@RequestParam("oid") int oid, Model model) {
        String result = ordersService.deleteOrder(oid);
        List<Orders> orders = ordersService.getAllOrders();
        model.addAttribute("result", result);
        model.addAttribute("orders", orders);
        return "orders";
    }

}
