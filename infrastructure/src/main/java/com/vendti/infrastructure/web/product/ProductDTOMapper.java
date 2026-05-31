package com.vendti.infrastructure.web.product;

import com.vendti.domain.model.Product;
import com.vendti.openapi.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDTOMapper {
    ProductDTO toDto(Product product);
    Product toDomain(ProductDTO productDTO);
}
