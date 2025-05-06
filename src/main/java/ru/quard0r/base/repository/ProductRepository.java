package ru.quard0r.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.quard0r.base.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
