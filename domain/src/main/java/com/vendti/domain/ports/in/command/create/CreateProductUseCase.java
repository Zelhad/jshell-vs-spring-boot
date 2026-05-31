package com.vendti.domain.ports.in.command.create;

import com.vendti.domain.model.Product;

public interface CreateProductUseCase {
    Product createProduct(Product product);
}
