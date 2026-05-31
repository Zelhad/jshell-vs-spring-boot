package com.vendti.domain.ports.out;

import com.vendti.domain.model.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);

    void deleteById(Long id);

    List<Product> findAll();
}
