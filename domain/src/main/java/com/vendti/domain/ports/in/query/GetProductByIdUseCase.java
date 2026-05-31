package com.vendti.domain.ports.in.query;

import com.vendti.domain.model.Product;

public interface GetProductByIdUseCase {

  Product findById(Long id);
}
