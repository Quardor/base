package ru.quard0r.base.controller;

import org.springframework.web.bind.annotation.*;
import ru.quard0r.base.dto.PersonDTO;
import ru.quard0r.base.dto.ProductDTO;
import ru.quard0r.base.entity.Product;
import ru.quard0r.base.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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

    @PostMapping("/save")
    public void save(@RequestBody Product product) {
        productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        productService.deleteById(id);
    }
}
