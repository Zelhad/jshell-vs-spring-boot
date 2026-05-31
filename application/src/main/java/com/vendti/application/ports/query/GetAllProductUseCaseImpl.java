package com.vendti.application.ports.query;

import com.vendti.domain.model.Product;
import com.vendti.domain.ports.in.query.GetAllProductUseCase;
import com.vendti.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllProductUseCaseImpl  implements GetAllProductUseCase {

    private final ProductRepository productRepository;


    @Override
    public List<Product> findAll() {
             return  productRepository.findAll();

    }
}
