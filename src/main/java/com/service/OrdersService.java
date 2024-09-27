package com.service;

import com.entity.Orders;
import com.entity.Product;
import com.entity.User;
import com.repository.OrdersRepository;
import com.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ProductRepository productRepository;

    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    public List<Orders> getOrdersByUid(int uid) {
        return ordersRepository.findOrdersByUid(uid);
    }

    public String storeOrder(int pid, int uid) {
        try {
            Optional<Product> product = productRepository.findById(pid);

            if (product.isEmpty()) throw new Exception("Product not found");

            Product p = product.get();
            if (p.getQuantity() == 0)  throw new Exception("Product out of stock");
            p.setQuantity(p.getQuantity() - 1);
            productRepository.saveAndFlush(p);

            Orders order = new Orders();
            order.setPid(p.getPid());
            order.setUid(uid);
            order.setOrderDate(LocalDateTime.now());
            order.setOrderTotal(p.getPrice());
            order.setProduct(p);
            ordersRepository.save(order);
            return "Order created successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deleteOrder(int oid) {
        try {
            Orders order = ordersRepository.findById(oid).orElse(null);
            if(order == null) throw new Exception("Order not found");

            productRepository.findById(order.getPid()).ifPresent(p -> {
                p.setQuantity(p.getQuantity() + 1);
                productRepository.save(p);
                ordersRepository.deleteById(oid);
            });

            return "Order deleted successfully";
        } catch (Exception e) {
            return "Order not deleted";
        }
    }

}
