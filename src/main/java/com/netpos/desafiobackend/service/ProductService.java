package com.netpos.desafiobackend.service;

import com.netpos.desafiobackend.dto.request.ProductStockUpdate;
import com.netpos.desafiobackend.entity.Product;
import com.netpos.desafiobackend.error.GenericError;

import java.util.List;

/**
 * @author brunocarneiro
 */
public interface ProductService extends CrudService<Product> {

    List<Product> findWithFilter(String name, String code, Integer userId) throws GenericError;

    Product stock(Product product, ProductStockUpdate update) throws GenericError;
}
