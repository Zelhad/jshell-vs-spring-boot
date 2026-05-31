package product.command.create;

import com.vendti.application.ports.command.create.CreateProductUseCaseImpl;
import com.vendti.domain.model.Product;
import com.vendti.domain.ports.out.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class TestCreateProductUseCaseImpl {
    @Mock
    private   ProductRepository productRepository;
    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCase;
    @Test
     void testCreateProductUseCase() {
        Product input = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Product")
                .href("https://example.com/product")
                .isBundle(false)
                .build();

        Product expected = Product.builder()
                .id(1L)
                .name("Test Product")
                .description("Test Product")
                .href("https://example.com/product")
                .isBundle(false)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(expected);

        Product result = createProductUseCase.createProduct(input);

        verify(productRepository, times(1)).save(any(Product.class));
        assertEquals(expected, result);
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getDescription(), result.getDescription());
        assertEquals(expected.getHref(), result.getHref());
        assertEquals(expected.getIsBundle(), result.getIsBundle());


    }

}
