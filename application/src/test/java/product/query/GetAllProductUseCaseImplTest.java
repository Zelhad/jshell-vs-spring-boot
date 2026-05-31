package product.query;

import com.vendti.application.ports.query.GetAllProductUseCaseImpl;
import com.vendti.domain.model.Product;
import com.vendti.domain.ports.out.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class GetAllProductUseCaseImplTest {
    @Mock
    private  ProductRepository productRepository;
    @InjectMocks
    GetAllProductUseCaseImpl getAllProductUseCase;
    @Test
     void getAllProductUseCase(){
        Product product1 = Product.builder()
                .id(1L)
                .name("Product1")
                .description("Description 1")
                .href("href1")
                .build();

        Product product2 =Product.builder()
                .id(1L)
                .name("Product1")
                .description("Description 1")
                .href("href1")
                .build();

        List<Product> expectedProducts = Arrays.asList(product1,product2);
        when(productRepository.findAll()).thenReturn(expectedProducts);
        List<Product> result = getAllProductUseCase.findAll();
        verify(productRepository, times(1)).findAll();
        assertEquals(expectedProducts, result);
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }

}
