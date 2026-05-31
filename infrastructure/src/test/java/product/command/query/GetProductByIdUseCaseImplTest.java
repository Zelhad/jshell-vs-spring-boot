package product.command.query;

import com.vendti.TestApplication;
import com.vendti.domain.ports.out.ProductRepository;
import com.vendti.infrastructure.web.product.ProductController;
import com.vendti.infrastructure.web.product.ProductDTOMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
    @Override
    public ResponseEntity<ProductDTO> getProductById(Long id) {
        Product product = getProductByIdUseCase.findById(id);
        ProductDTO productDTO = productDTOMapper.toDto(product);

        log.info("Get: GetProduct by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }
 */
//@SpringBootTest(classes = TestApplication.class)
@SpringBootTest(classes = TestApplication.class)
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class GetProductByIdUseCaseImplTest {
    @Autowired
    private ProductController productController;
    @MockBean
    ProductRepository productRepository;

    @MockBean
    ProductDTOMapper productApiMapper;

}
