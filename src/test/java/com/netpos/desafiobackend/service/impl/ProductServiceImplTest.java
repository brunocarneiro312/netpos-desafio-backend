package com.netpos.desafiobackend.service.impl;

import com.netpos.desafiobackend.entity.Product;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.repository.ProductRepository;
import com.netpos.desafiobackend.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        this.productService = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("Given product, when post, then return product saved")
    public void givenProduct_whenPost_thenReturnProductSaved() throws GenericError {

        // given
        Product product = getProduct();

        given(this.productRepository.save(any())).willReturn(product);

        // when
        Product savedProduct = this.productService.save(product);

        // then
        assertAll("Save",
                () -> assertNotNull(savedProduct, "O produto não deveria estar nulo"),
                () -> assertEquals(savedProduct.toString(), product.toString(), "Os produtos deveriam ser iguais"));
    }

    @Test
    @DisplayName("Given product, when put, then return product updated")
    public void givenProduct_whenPut_thenReturnProductUpdated() throws GenericError {

        // given
        Product product = getProduct();
        product.setName("updated");

        given(this.productRepository.saveAndFlush(any())).willReturn(product);

        // when
        Product updatedProduct = this.productService.update(product);

        // then
        assertAll("Update",
                () -> assertNotNull(updatedProduct, "O produto não deveria estar nulo"),
                () -> assertEquals("updated", updatedProduct.getName()));
    }

    @Test
    @DisplayName("Given product id, when delete, then return product deleted")
    public void givenProductId_whenDelete_thenReturnProductDeleted() throws GenericError {

        // given
        Integer id = 1;

        given(this.productRepository.findById(id)).willReturn(Optional.of(getProduct()));

        // when
        Product deletedProduct = this.productService.delete(id);

        // then
        assertAll("Delete",
                () -> assertNotNull(deletedProduct));
    }

    @Test
    @DisplayName("Given product id, when get, then return product")
    public void givenProductId_whenGet_thenReturnProduct() throws GenericError {

        // given
        Integer id = 1;

        given(this.productRepository.findById(id)).willReturn(Optional.of(getProduct()));

        // when
        Product recoveredProduct = this.productService.findById(id);

        // then
        assertAll("FindById",
                () -> assertNotNull(recoveredProduct));
    }

    @Test
    @DisplayName("When list, return list of products")
    public void whenList_thenReturnListOfProducts() throws GenericError {

        // fixture
        given(this.productRepository.findAll()).willReturn(Arrays.asList(getProduct(), getProduct()));

        // when
        List<Product> products = this.productService.findAll();

        // then
        assertAll("FindAll",
                () -> assertNotNull(products),
                () -> assertEquals(2, products.size()));
    }

    public Product getProduct() {
        return new Product.Builder()
                .name("product test")
                .code(UUID.randomUUID().toString())
                .price(10.0F)
                .build();
    }

}