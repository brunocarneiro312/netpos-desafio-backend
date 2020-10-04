package com.netpos.desafiobackend.service.impl;

import com.netpos.desafiobackend.entity.Product;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.repository.ProductRepository;
import com.netpos.desafiobackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService  {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws GenericError {
        return Optional.of(this.productRepository.save(product))
                .orElseThrow(GenericError::new);
    }

    @Override
    public Product update(Product product) throws GenericError {
        return Optional.of(this.productRepository.saveAndFlush(product))
                .orElseThrow(GenericError::new);
    }

    @Override
    public Product delete(Integer id) throws GenericError {
        Product productRemoved = findById(id);
        if (productRemoved == null) {
            throw new GenericError();
        }
        this.productRepository.delete(productRemoved);
        return productRemoved;
    }

    @Override
    public Product findById(Integer id) throws GenericError {
        return Optional.of(this.productRepository.findById(id.longValue()).get())
                .orElseThrow(GenericError::new);
    }

    @Override
    public List<Product> findAll() throws GenericError {
        return Optional.of(this.productRepository.findAll())
                .orElseThrow(GenericError::new);
    }
}
