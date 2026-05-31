package product.command.delete;

import com.vendti.application.ports.command.delete.DeleteProductUseCaseImpl;
import com.vendti.domain.exception.product.ProductNotFoundException;
import com.vendti.domain.ports.out.ProductRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DeleteProductUseCaseImplTest {
    @Mock
    private  ProductRepository productRepository;
    @InjectMocks
    private  DeleteProductUseCaseImpl deleteProductUseCaseImpl;

    @Test
    @Order(3)
    void executeDeleteProductByIdWhenFound (){
        deleteProductUseCaseImpl.execute(1L);
        verify(productRepository).deleteById(1L);


    }
    @Order(4)
    @Test
    void executeDeleteProductByIdWhenNotFound (){
        doThrow(new ProductNotFoundException(99L)).when(productRepository).deleteById(99L);
        assertThrows(ProductNotFoundException.class, () -> deleteProductUseCaseImpl.execute(99L));
    }
}
