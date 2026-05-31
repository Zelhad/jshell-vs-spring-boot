package com.vendti.application.ports.query;

import com.vendti.domain.model.Product;
import com.vendti.domain.ports.in.query.GetProductByIdUseCase;
import com.vendti.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class GetProductByIdUseCaseImpl implements GetProductByIdUseCase {
    private final ProductRepository productRepository;


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }
}
