package com.vendti.infrastructure.repository.product;

import com.vendti.domain.exception.product.ProductNotFoundException;
import com.vendti.domain.model.Product;
import com.vendti.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductSpringJpaRepository productSpringJpaRepository;
    private final ProductEntityMapper productEntityMapper;

    @Override
    public Product save(Product product) {
        ProductEntity entity = productEntityMapper.toEntity(product);
        ProductEntity saved = productSpringJpaRepository.save(entity);
        return productEntityMapper.toDomain(saved);
    }

    @Override
    public Product findById(Long id) {
        return productSpringJpaRepository.findById(id)
                .map(productEntityMapper::toDomain)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void deleteById(Long id) {
        if (productSpringJpaRepository.findById(id).isEmpty()) {
            throw new ProductNotFoundException(id);
        } else {
            productSpringJpaRepository.deleteById(id);
        }
    }

    @Override
    public List<Product> findAll() {
        return productSpringJpaRepository.findAll()
                .stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }
}

