package com.netpos.desafiobackend.controller;

import com.netpos.desafiobackend.dto.request.ProductCreateRequest;
import com.netpos.desafiobackend.dto.request.ProductStockUpdate;
import com.netpos.desafiobackend.dto.request.ProductUpdateRequest;
import com.netpos.desafiobackend.entity.Product;
import com.netpos.desafiobackend.error.GenericError;
import com.netpos.desafiobackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * --------------------------------------
     * Listar produtos com ordenação e filtro
     * --------------------------------------
     * @param userId
     * @param filter: filtra pelo nome
     * @param order: code, name ou price
     * @param type: asc, desc
     * @return
     */
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Product>> list(@RequestHeader("user_id") Integer userId,
                                              @RequestParam(value = "filter", required = false) String filter,
                                              @RequestParam(value = "order", required = false) String order,
                                              @RequestParam(value = "type", required = false) String type) {
        try {
            return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * -----------------------------------------
     * Cria um novo produto para um user account
     * -----------------------------------------
     * @param userId
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<Product> post(@RequestHeader("user_id") Integer userId,
                                        @RequestBody ProductCreateRequest request) {
        try {
            return new ResponseEntity<>(this.productService.save(null), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * -----------------
     * Altera um produto
     * -----------------
     * @param userId
     * @param productId
     * @param updateRequest
     * @return
     */
    @PutMapping("/{product_id}")
    public ResponseEntity<Product> put(@RequestHeader("user_id") Integer userId,
                                       @PathVariable("product_id") Integer productId,
                                       @RequestBody ProductUpdateRequest updateRequest) {
        try {
            return new ResponseEntity<>(this.productService.update(null), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * ------------------------------------------
     * Deleta um produto (não deleta fisicamente)
     * ------------------------------------------
     * @param userId
     * @param productId
     * @return
     */
    @DeleteMapping("{/product_id}")
    public ResponseEntity<Product> delete(@RequestHeader("user_id") Integer userId,
                                          @PathVariable("product_id") Integer productId) {
        try {
            return new ResponseEntity<>(this.productService.delete(null), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * ---------------------------------
     * Retorna os detalhes de um produto
     * ---------------------------------
     * @param userId
     * @param productId
     * @return
     */
    @GetMapping("/{product_id}")
    public ResponseEntity<Product> get(@RequestHeader("user_id") Integer userId,
                                       @PathVariable("product_id") Integer productId) {
        try {
            return new ResponseEntity<>(this.productService.findById(productId), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * --------------------------------------------------------------------------
     * Realiza uma operação de entrada ou saida de estoque de determinado produto
     * --------------------------------------------------------------------------
     * @param userId
     * @param productId
     * @param stockUpdate
     * @return
     */
    @PostMapping("/{product_id}/stock")
    public ResponseEntity<Product> stock(@RequestHeader("user_id") Integer userId,
                                         @PathVariable("product_id") Integer productId,
                                         @RequestBody ProductStockUpdate stockUpdate) {
        try {
            return new ResponseEntity<>(this.productService.findById(productId), HttpStatus.OK);
        }
        catch (GenericError e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
