package com.service;

import com.entity.Product;
import com.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProduct(int pid) {
        return productRepository.findById(pid).orElse(null);
    }

    public String storeProduct(Product product) {
        try {
            productRepository.save(product);
            return "Product added successfully";
        } catch (Exception e) {
            return "Product not added";
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllAvailableProducts();
    }

    public String updateProduct(int pid, Product updatedProduct) {
        try {
            Optional<Product> product = productRepository.findById(pid);
            if(product.isPresent()){
                Product p = product.get();
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
                p.setQuantity(updatedProduct.getQuantity());
                p.setImage(updatedProduct.getImage());
                p.setDescription(updatedProduct.getDescription());
                productRepository.save(p);
                return "Product updated successfully";
            }
            return "Product not found";
        } catch (Exception e) {
            return "Product not updated";
        }
    }

    public List<Product> filterProducts(String name, Float price) {
        return productRepository.findProductsByNameAndPrice(name, price);
    }

    public String deleteProduct(int pid) {
        try {
            productRepository.deleteById(pid);
            return "Product deleted successfully";
        } catch (Exception e) {
            return "Product not deleted";
        }
    }

}
