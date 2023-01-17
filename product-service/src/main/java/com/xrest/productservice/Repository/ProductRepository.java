package com.xrest.productservice.Repository;

import com.xrest.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product ,Long> {
}
