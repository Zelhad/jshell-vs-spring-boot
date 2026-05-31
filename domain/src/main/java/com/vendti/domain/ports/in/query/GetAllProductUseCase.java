package com.vendti.domain.ports.in.query;

import com.vendti.domain.model.Product;
import java.util.List;
public interface GetAllProductUseCase {
    List<Product> findAll();
}
