package ru.quard0r.base.controller;

import org.springframework.web.bind.annotation.*;
import ru.quard0r.base.dto.ProductDTO;
import ru.quard0r.base.entity.Category;
import ru.quard0r.base.entity.Product;
import ru.quard0r.base.service.CategoryService;
import ru.quard0r.base.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<ProductDTO> findAll() {
        List<ProductDTO> products = new ArrayList<>();
        productService.findAll().forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setCategories(product.getCategories());
            products.add(productDTO);
        });
        return products;
    }

    @GetMapping("/get/{id}")
    public ProductDTO findById(@PathVariable long id) {
        ProductDTO productDTO = new ProductDTO();
        productService.findById(id).ifPresent(product -> {
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setCategories(product.getCategories());
        });
        return productDTO;
    }

    @GetMapping("/get/categories")
    public List<ProductDTO> findByCategory(@RequestParam List<Long> ids) {
        List<Category> categories = categoryService.findAllByIds(ids);
        List<ProductDTO> products = new ArrayList<>();
        productService.findByCategories(categories).forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setCategories(product.getCategories());
            products.add(productDTO);
        });
        return products;
    }

    @PostMapping("/save")
    public void save(@RequestBody ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategories(productDTO.getCategories());
        productService.save(product);
    }

    @PutMapping("/update")
    public void update(@RequestBody ProductDTO productDTO) {
        productService.findById(productDTO.getId()).ifPresent(product -> {
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setCategories(productDTO.getCategories());
            productService.save(product);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        productService.deleteById(id);
    }
}
