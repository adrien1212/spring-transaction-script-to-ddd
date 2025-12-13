package fr.adriencaubel.tstoddd;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import fr.adriencaubel.tstoddd.domain.model.article.MaterialState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArticleTest {

    private MaterialState availableState;
    private MaterialState unavailableState;

    @BeforeEach
    void setUp() {
        availableState = mock(MaterialState.class);
        when(availableState.isAvailable()).thenReturn(true);

        unavailableState = mock(MaterialState.class);
        when(unavailableState.isAvailable()).thenReturn(false);
    }

    @Test
    void shouldDecrementStockWhenValid() {
        Article article = new Article("Cable", availableState, 10);

        article.decrementStock(3);

        assertEquals(7, article.getStock());
    }

    @Test
    void shouldThrowWhenAmountIsZero() {
        Article article = new Article("Cable", availableState, 10);

        assertThrows(IllegalArgumentException.class, () -> article.decrementStock(0));
    }

    @Test
    void shouldThrowWhenAmountIsNegative() {
        Article article = new Article("Cable", availableState, 10);

        assertThrows(IllegalArgumentException.class, () -> article.decrementStock(-5));
    }

    @Test
    void shouldThrowWhenStockIsTooLow() {
        Article article = new Article("Cable", availableState, 2);

        assertThrows(IllegalArgumentException.class, () -> article.decrementStock(5));
    }

    @Test
    void shouldThrowWhenMaterialIsUnavailable() {
        Article article = new Article("Cable", unavailableState, 10);

        assertThrows(IllegalArgumentException.class, () -> article.decrementStock(3));
    }
}
