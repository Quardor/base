package ru.quard0r.base.controller;

import org.springframework.web.bind.annotation.*;
import ru.quard0r.base.dto.CategoryDTO;
import ru.quard0r.base.entity.Category;
import ru.quard0r.base.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categories = new ArrayList<>();
        categoryService.findAll().forEach(category -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
            categories.add(categoryDTO);
        });
        return categories;
    }

    @GetMapping("/get/{id}")
    public CategoryDTO findById(@PathVariable long id) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryService.findById(id).ifPresent(category -> {
            categoryDTO.setId(category.getId());
            categoryDTO.setName(category.getName());
        });
        return categoryDTO;
    }

    @PostMapping("/save")
    public void save(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        categoryService.save(category);
    }

    @PutMapping("/update")
    public void update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.findById(categoryDTO.getId()).ifPresent(category -> {
            category.setName(categoryDTO.getName());
            categoryService.save(category);
        });
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        categoryService.deleteById(id);
    }
}
