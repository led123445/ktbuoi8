package com.example.NHANVIEN.service;

import com.example.NHANVIEN.model.Product;
import com.example.NHANVIEN.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    // Retrieve all products from the database
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // Retrieve a product by its id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    // Add a new product to the database
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    // Update an existing product
    public Product updateProduct(@NotNull Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        updatedProduct.getId() + " does not exist."));

        // Update the product's properties
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setNamenv(updatedProduct.getNamenv());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setThumnail(updatedProduct.getThumnail());


        // Save the updated product
        return productRepository.save(existingProduct);
    }
    // Delete a product by its id
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }
}