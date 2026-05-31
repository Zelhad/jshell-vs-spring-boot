package com.vendti.infrastructure.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSpringJpaRepository extends JpaRepository<ProductEntity, Long> {
}
