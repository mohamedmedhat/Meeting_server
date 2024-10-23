package com.example.demo.service.product;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Image;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ImageService imageService;

    @Override
    public Boolean createProduct(String name, BigDecimal price, MultipartFile file) throws IOException {
        Image image = this.imageService.saveImage(file);
        Product product = this.productMapper.toEntity(name, price, image);
        this.productRepository.save(product);
        return true;
    }

    @Async
    @Transactional
    @Override
    public CompletableFuture<Product> updateProduct(String id, String name, BigDecimal price, MultipartFile file) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Product product = this.findProductById(id);
                Image image = this.imageService.saveImage(file);
                this.imageService.deleteImage(product.getImage());
                Product updatedProduct = this.productMapper.updateToEntity(product, name, price, image);
                return this.productRepository.save(updatedProduct);
            } catch (IOException e) {
                throw new RuntimeException("Failed to update product due to image upload error", e);
            }
        });
    }


    @Override
    public ResponseEntity<Void> deleteProduct(String id) {
        Product product = this.findProductById(id);
        this.productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }

    @Override
    public Product findProductById(String id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    @Async
    @Override
    public CompletableFuture<Page<Product>> findAll(int page, int size) {
        return CompletableFuture.supplyAsync(() -> {
            Pageable pageable = PageRequest.of(page, size);
            return this.productRepository.findAll(pageable);
        });
    }

    @Async
    @Override
    public Page<Product> findProductsByHighestPrice(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.productRepository.findTopByOrderByPriceDesc(pageable);
    }

    @Async
    @Override
    public Page<Product> findProductsByLowestPrice(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.productRepository.findTopByOrderByPriceAsc(pageable);
    }

    @Async
    @Override
    public Page<Product> findProductsInRange(BigDecimal start, BigDecimal end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.productRepository.findByPriceLessThanEqualAndPriceGreaterThanEqual(start, end, pageable);
    }

    @Async
    @Override
    public Page<Product> findProductsByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.productRepository.findByName(name, pageable);
    }
}
