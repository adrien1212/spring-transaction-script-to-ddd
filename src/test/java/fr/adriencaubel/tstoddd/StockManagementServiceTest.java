package fr.adriencaubel.tstoddd;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import fr.adriencaubel.tstoddd.domain.service.StockManagementService;
import fr.adriencaubel.tstoddd.domain.service.SupplierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockManagementServiceTest {

    @Mock
    private SupplierService supplierService;

    @Mock
    private Article article;

    @InjectMocks
    private StockManagementService stockService;

    @Test
    void shouldDecrementStock_whenArticleIsAvailable_andStockIsSufficient() {
        // given
        when(article.isAvailableToSell()).thenReturn(true);
        when(article.getStock()).thenReturn(10);

        // when
        stockService.checkAndDecrementStock(article, 2);

        // then
        verify(article).decrementStock(2);
        verifyNoInteractions(supplierService);
    }

    @Test
    void shouldTriggerSupplierCommand_whenStockIsLow() {
        // given
        when(article.isAvailableToSell()).thenReturn(true);
        when(article.getStock()).thenReturn(3);

        // when
        stockService.checkAndDecrementStock(article, 1);

        // then
        verify(supplierService).passCommand(article);
        verify(article).decrementStock(1);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenArticleIsNull() {
        // when / then
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class,
                        () -> stockService.checkAndDecrementStock(null, 2));

        assertEquals("Article cannot be null", exception.getMessage());
        verifyNoInteractions(supplierService);
    }

    @Test
    void shouldThrowIllegalStateException_whenArticleNotAvailable() {
        // given
        when(article.isAvailableToSell()).thenReturn(false);

        // when / then
        IllegalStateException exception =
                assertThrows(IllegalStateException.class,
                        () -> stockService.checkAndDecrementStock(article, 2));

        assertEquals("Article is not available for sale", exception.getMessage());

        verify(article, never()).decrementStock(anyInt());
        verifyNoInteractions(supplierService);
    }
}