package dev.vidushee.inventory.service;

import dev.vidushee.inventory.entity.Product;
import dev.vidushee.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No product found with id: " + id)
        );
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Product product, String id) {
        Product oldProduct = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No product found with id: " + id)
        );
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());
        return productRepository.save(oldProduct);
    }
    
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No product found with id: " + id)
        );
        productRepository.delete(product);
    }
}