package com.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;
    private Integer uid;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "order_total")
    private float orderTotal;
    private Integer pid;
    @ManyToOne
    @JoinColumn(name = "uid", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "pid", insertable = false, updatable = false)
    private Product product;

    public Orders() {
    }

    public Orders(int oid, int uid, LocalDateTime orderDate, float orderTotal, Integer pid, Product product) {
        this.oid = oid;
        this.uid = uid;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.pid = pid;
        this.product = product;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", orderDate=" + orderDate +
                ", orderTotal=" + orderTotal +
                ", pid=" + pid +
                ", productId=" + (product != null ? product.getPid() : null) +
                '}';
    }
}
