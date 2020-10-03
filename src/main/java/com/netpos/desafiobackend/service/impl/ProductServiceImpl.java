package com.netpos.desafiobackend.service.impl;

import com.netpos.desafiobackend.entity.Product;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.repository.ProductRepository;
import com.netpos.desafiobackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService  {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws GenericError {
        return null;
    }

    @Override
    public Product findById(Integer id) throws GenericError {
        return null;
    }

    @Override
    public List<Product> findAll() throws GenericError {
        return null;
    }
}
