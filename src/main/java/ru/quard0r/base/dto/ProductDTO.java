package ru.quard0r.base.dto;

import lombok.Getter;
import lombok.Setter;
import ru.quard0r.base.entity.Category;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    private long id;
    private String name;
    private double price;
    private List<Category> categories;
}
