package br.com.lm.usecaseimpl;

import br.com.lm.entity.Product;
import br.com.lm.exception.ProductAlreadyExistsException;
import br.com.lm.gateway.ProductGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseImplTest {

    @Mock
    private ProductGateway productGateway;

    @InjectMocks
    private CreateProductUseCaseImpl createProductUseCase;

    @Test
    void whenProductDoesNotExist_thenSaveProduct() {
        Product product = new Product("Test", "T", 10, 5.0, "ind", "EUA");

        when(productGateway.existsByProductAndType("Test", "T")).thenReturn(false);
        when(productGateway.save(product)).thenReturn(product);

        Product result = createProductUseCase.createProduct(product);

        assertEquals(product, result);
        verify(productGateway).existsByProductAndType("Test", "T");
        verify(productGateway).save(product);
    }

    @Test
    void whenProductExists_thenThrowException() {
        Product product = new Product("Test", "T", 10, 5.0, "ind", "EUA");

        when(productGateway.existsByProductAndType("Test", "T")).thenReturn(true);

        assertThrows(ProductAlreadyExistsException.class, () ->
                createProductUseCase.createProduct(product));
    }
}