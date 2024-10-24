package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.security.CurrentUser;
import com.example.demo.service.product.IProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Products", description = "not authorized till now")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/products/")
public class ProductController {
    private final IProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Boolean addProduct(
            @RequestParam("name") @Valid @NotBlank String name,
            @RequestParam("price") @Valid @NotNull @DecimalMin("0.1") BigDecimal price,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        return this.productService.createProduct(name, price, file);
    }

    @PutMapping("{id}")
    public CompletableFuture<Product> updateProduct(
            @PathVariable("id") String id,
            @RequestParam("name") String name,
            @RequestParam("price") BigDecimal price,
            @RequestParam("image") MultipartFile file) throws IOException {
        return this.productService.updateProduct(id, name, price, file);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        return this.productService.deleteProduct(id);
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return this.productService.findProductById(id);
    }

    @GetMapping("{page}/{size}")
    public CompletableFuture<Page<Product>> getAllProducts(@PathVariable("page") int page, @PathVariable("size") int size) {
        return this.productService.findAll(page, size);
    }

    @PostMapping("add-to-Cart/{id}")
    public Boolean addToCart(@PathVariable("id") String id, @CurrentUser() User user) {
        return this.productService.addProductToCart(id, user);
    }
}
