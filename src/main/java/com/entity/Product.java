package com.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String name;
    private float price;
    private String description;
    private String image;
    private int quantity;

    @OneToMany(mappedBy = "product")
    private List<Orders> orders;

    public Product() {
    }

    public Product(int pid, String name, float price, String description, String image, int quantity) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + pid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
