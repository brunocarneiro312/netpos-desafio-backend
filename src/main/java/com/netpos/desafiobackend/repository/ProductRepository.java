package com.netpos.desafiobackend.repository;

import com.netpos.desafiobackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Filtra os produtos pelo código ou pelo nome
     */
    List<Product> findByNameStartingWithOrCodeStartingWithAndUserAccountId(String name, String code, Integer userId);

}
