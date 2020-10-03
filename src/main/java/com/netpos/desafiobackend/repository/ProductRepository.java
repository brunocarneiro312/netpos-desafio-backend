package com.netpos.desafiobackend.repository;

import com.netpos.desafiobackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
