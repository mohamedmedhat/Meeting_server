package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

public interface IProductService {
    Boolean createProduct(String name, BigDecimal price, MultipartFile file) throws IOException;

    CompletableFuture<Product> updateProduct(String id, String name, BigDecimal price, MultipartFile file);

    ResponseEntity<Void> deleteProduct(String id);

    Product findProductById(String id);

    Boolean addProductToCart(String productId, User user);

    CompletableFuture<Page<Product>> findAll(int page, int size);

    Page<Product> findProductsByHighestPrice(int page, int size);

    Page<Product> findProductsByLowestPrice(int page, int size);

    Page<Product> findProductsInRange(BigDecimal start, BigDecimal end, int page, int size);

    Page<Product> findProductsByName(String name, int page, int size);
}
