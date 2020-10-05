package com.netpos.desafiobackend.service.impl;

import com.netpos.desafiobackend.dto.request.ProductStockUpdate;
import com.netpos.desafiobackend.entity.Product;
import com.netpos.desafiobackend.entity.Stock;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.repository.ProductRepository;
import com.netpos.desafiobackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * @author brunocarneiro
 */
@Service
public class ProductServiceImpl implements ProductService  {

    private final ProductRepository productRepository;

    private final Predicate<Stock> maxQuantityReached = stock -> stock.getQuantity() >= 1000;
    private final Predicate<Stock> minQuantityReached = stock -> stock.getQuantity() <= 0;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws GenericError {
        product.setCode(UUID.randomUUID().toString());
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
        return Optional.of(this.productRepository.findById(id).get())
                .orElseThrow(GenericError::new);
    }

    @Override
    public List<Product> findAll() throws GenericError {
        return Optional.of(this.productRepository.findAll())
                .orElseThrow(GenericError::new);
    }

    @Override
    public List<Product> findWithFilter(String name, String code, Integer userId) throws GenericError {
        return Optional.of(
                this.productRepository.findByNameStartingWithOrCodeStartingWithAndUserAccountId(name, code, userId))
                .orElseThrow(GenericError::new);
    }

    @Override
    public Product stock(Product product, ProductStockUpdate update) throws GenericError {
        switch (update.getOperation()) {
            case ADD:
                product = addStock(product, update.getQuantity());
                break;
            case SET:
                product = setStock(product, update.getQuantity());
                break;
            case REMOVE:
                product = removeStock(product, update.getQuantity());
                break;
            default:
                throw new GenericError();
        }
        return this.productRepository.saveAndFlush(product);
    }

    /**
     * Métodos auxiliares
     */
    private Product addStock(Product product, Integer quantity) {
        product.getStock().setQuantity(product.getStock().getQuantity() + quantity);
        return fixQuantity(product);
    }

    private Product setStock(Product product, Integer quantity) {
        product.getStock().setQuantity(quantity);
        return fixQuantity(product);
    }

    private Product removeStock(Product product, Integer quantity) {
        product.getStock().setQuantity(product.getStock().getQuantity() - quantity);
        return fixQuantity(product);
    }

    private Product fixQuantity(Product product) {
        if (this.maxQuantityReached.test(product.getStock())) {
            product.getStock().setQuantity(1000);
            return product;
        }
        if (this.minQuantityReached.test(product.getStock())) {
            product.getStock().setQuantity(0);
            return product;
        }
        return product;
    }
}
