package ru.quard0r.base.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.quard0r.base.entity.Category;
import ru.quard0r.base.entity.Product;
import ru.quard0r.base.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategories(List<Category> categories) {
        return productRepository.findByCategories(categories);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}
