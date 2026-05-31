package product.command.query;

import com.vendti.TestApplication;
import com.vendti.domain.model.Product;
import com.vendti.domain.ports.out.ProductRepository;
import com.vendti.infrastructure.web.product.ProductController;
import com.vendti.infrastructure.web.product.ProductDTOMapper; // ← Import the mapper
import com.vendti.openapi.model.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TestApplication.class)
@ExtendWith({SpringExtension.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class GetAllApiProductUseCaseImplTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductRepository productRepository;

    @MockBean  // ← Mock the mapper since it's a direct dependency of the controller
    private ProductDTOMapper productDTOMapper;

    @TestConfiguration
    static class TestContextConfiguration {
        // Add custom beans here if needed for the test context
    }

    @Test
    void whenQueriedForAllProducts_shouldReturnAllProductsAsDTOs() {

        // Arrange: Prepare domain entities

        var domainProducts = List.of(
                Product.builder()
                        .id(1L)
                        .name("Laptop")
                        .description("A high-performance laptop")
                        .href("http://example.com/laptop")
                        .isBundle(false)
                        .build(),
                Product.builder()
                        .id(2L)
                        .name("Keyboard")
                        .description("A mechanical keyboard")
                        .href("http://example.com/keyboard")
                        .isBundle(true)
                        .build()
        );

        // Prepare expected DTOs (match your ProductDTO builder/setter style)
        var expectedDto1 = new ProductDTO()
                .id(1L)
                .name("Laptop")
                .description("A high-performance laptop")
                .href("http://example.com/laptop")
                .isBundle(false);

        var expectedDto2 = new ProductDTO()
                .id(2L)
                .name("Keyboard")
                .description("A mechanical keyboard")
                .href("http://example.com/keyboard")
                .isBundle(true);

        // Mock repository
        when(productRepository.findAll()).thenReturn(domainProducts);

        // Mock mapper for each product → DTO conversion
        when(productDTOMapper.toDto(domainProducts.get(0))).thenReturn(expectedDto1);
        when(productDTOMapper.toDto(domainProducts.get(1))).thenReturn(expectedDto2);

        // Act: Call the controller method directly
        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        // Assert: Validate response structure and content
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .isNotNull()
                .hasSize(2)
                .extracting(ProductDTO::getName)
                .containsExactly("Laptop", "Keyboard");

        assertThat(response.getBody().get(0))
                .extracting("id", "description", "href", "isBundle")
                .containsExactly(1L, "A high-performance laptop", "http://example.com/laptop", false);

        assertThat(response.getBody().get(1))
                .extracting("id", "description", "href", "isBundle")
                .containsExactly(2L, "A mechanical keyboard", "http://example.com/keyboard", true);

        // Verify interactions
        verify(productRepository, times(1)).findAll();
        verify(productDTOMapper, times(1)).toDto(domainProducts.get(0));
        verify(productDTOMapper, times(1)).toDto(domainProducts.get(1));
        verifyNoMoreInteractions(productDTOMapper, productRepository);
    }

    @Test
    void whenNoProductsExist_shouldReturnEmptyList() {
        // Arrange
        when(productRepository.findAll()).thenReturn(List.of());

        // Act
        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull().isEmpty();

        verify(productRepository, times(1)).findAll();
        // Mapper should never be called when list is empty
        verify(productDTOMapper, never()).toDto(any(Product.class));
    }

    @Test
    void whenRepositoryThrowsException_shouldPropagate() {
        // Arrange
        when(productRepository.findAll()).thenThrow(new RuntimeException("DB unavailable"));

        // Act & Assert
        assertThatThrownBy(() -> productController.getAllProducts())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("DB unavailable");

        verify(productRepository, times(1)).findAll();
        // Mapper should never be called if repository fails
        verify(productDTOMapper, never()).toDto(any(Product.class));
    }
}