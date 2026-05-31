package com.vendti.application.ports.command.delete;

import com.vendti.domain.ports.in.command.delete.DeleteProductUseCase;
import com.vendti.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public void execute(Long productId) {
        productRepository.deleteById(productId);
    }
}
