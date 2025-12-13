package fr.adriencaubel.tstoddd;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import fr.adriencaubel.tstoddd.domain.model.order.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderTest {

    @Test
    void shouldCreateOrderWithValidArticleAndQuantity() {
        Article article = mock(Article.class);
        when(article.isAvailableToSell()).thenReturn(true);
        when(article.getId()).thenReturn(123L);

        Order order = new Order(1L, article, 2);

        assertEquals(1, order.getLines().size());
        assertEquals(123L, order.getLines().get(0).getArticleId());
    }

    @Test
    void shouldThrowIfQuantityIsZero() {
        Article article = mock(Article.class);
        when(article.isAvailableToSell()).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> new Order(1L, article, 0));
    }

    @Test
    void shouldThrowIfArticleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Order(1L, null, 2));
    }

    @Test
    void shouldThrowIfArticleNotAvailable() {
        Article article = mock(Article.class);
        when(article.isAvailableToSell()).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> new Order(1L, article, 2));
    }
}