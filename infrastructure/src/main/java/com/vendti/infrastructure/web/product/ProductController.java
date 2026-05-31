package com.vendti.infrastructure.web.product;

import com.vendti.domain.model.Product;
import com.vendti.domain.ports.in.command.create.CreateProductUseCase;
import com.vendti.domain.ports.in.command.delete.DeleteProductUseCase;
import com.vendti.domain.ports.in.query.GetAllProductUseCase;
import com.vendti.domain.ports.in.query.GetProductByIdUseCase;
import com.vendti.openapi.api.ProductsApi;
import com.vendti.openapi.model.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
@Slf4j
public class ProductController implements ProductsApi {

    private final DeleteProductUseCase deleteProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final GetAllProductUseCase getAllProductUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;

    private final ProductDTOMapper productDTOMapper;

    @Override
    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
        Product product = productDTOMapper.toDomain(productDTO);
        Product savedProduct = createProductUseCase.createProduct(product);
        ProductDTO responseDTO = productDTOMapper.toDto(savedProduct);

        log.info("Product created with id: {}", savedProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Override
    public ResponseEntity<Void> deleteProductById(Long id) {
        log.info("Delete: DeleteProduct with id: {}", id);
        deleteProductUseCase.execute(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = getAllProductUseCase.findAll();
        List<ProductDTO> dto = products.stream()
                .map(productDTOMapper::toDto)
                .toList();
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<ProductDTO> getProductById(Long id) {
        Product product = getProductByIdUseCase.findById(id);
        ProductDTO productDTO = productDTOMapper.toDto(product);

        log.info("Get: GetProduct by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }
}
