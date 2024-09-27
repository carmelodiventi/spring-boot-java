package com.repository;

import com.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.quantity > 0")
    public List<Product> findAllAvailableProducts();

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND p.price >= :price")
    public List<Product> findProductsByNameAndPrice(@Param("name") String name, @Param("price") Float price);

}
