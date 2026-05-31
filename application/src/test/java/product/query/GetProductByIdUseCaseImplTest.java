package product.query;
import com.vendti.application.ports.query.GetProductByIdUseCaseImpl;
import com.vendti.domain.exception.product.ProductNotFoundException;
import com.vendti.domain.model.Product;
import com.vendti.domain.ports.out.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class GetProductByIdUseCaseImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private GetProductByIdUseCaseImpl getProductByIdUseCaseImpl;

@Test
@Order(1)
    void shoudReturnProductByIdWhenFound() {

    Product productData = Product.builder()
            .id(1L)
            .name("productNameTest")
            .description("productDescriptionTest")
            .href("https://vendtiApi/products/get")
            .isBundle(false)
            .build();
    when(productRepository.findById(1L)).thenReturn(productData);

    Product product = getProductByIdUseCaseImpl.findById(1L);

    assertNotNull(product);
    assertEquals(1L, product.getId());
    assertEquals("productNameTest", product.getName());
    assertEquals("productDescriptionTest", product.getDescription());
    assertEquals("https://vendtiApi/products/get", product.getHref());
    assertEquals(false, product.getIsBundle());

    verify(productRepository, times(1)).findById(1L);

}
    @Test
    @Order(2)
    void shouldThrowWhenNotFound() {

        when(productRepository.findById(99L)).thenThrow(new ProductNotFoundException(99L));

        assertThrows(ProductNotFoundException.class, () ->
                getProductByIdUseCaseImpl.findById(99L)
        );
    }


}
