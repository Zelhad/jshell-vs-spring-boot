package com.vendti.application.ports.command.create;

import com.vendti.domain.model.Product;
import com.vendti.domain.ports.in.command.create.CreateProductUseCase;
import com.vendti.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class CreateProductUseCaseImpl  implements CreateProductUseCase {
    private  final ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
