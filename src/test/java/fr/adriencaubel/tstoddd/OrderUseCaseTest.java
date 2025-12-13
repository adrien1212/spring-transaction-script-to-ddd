package fr.adriencaubel.tstoddd;

import fr.adriencaubel.tstoddd.application.order.OrderUseCase;
import fr.adriencaubel.tstoddd.domain.model.article.Article;
import fr.adriencaubel.tstoddd.domain.model.article.MaterialState;
import fr.adriencaubel.tstoddd.domain.model.order.Order;
import fr.adriencaubel.tstoddd.domain.service.StockManagementService;
import fr.adriencaubel.tstoddd.infrastructure.db.ArticleRepository;
import fr.adriencaubel.tstoddd.infrastructure.db.OrderRepository;
import fr.adriencaubel.tstoddd.infrastructure.web.dto.CreateOrderRequestModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Spy
    private StockManagementService stockService;

    @InjectMocks
    private OrderUseCase orderUseCase;

    @Test
    void shouldCreateOrderSuccessfully() {
        Long articleId = 10L;
        Long clientId = 1L;
        int quantity = 2;

        MaterialState availableState = mock(MaterialState.class);
        when(availableState.isAvailable()).thenReturn(true);
        Article article = new Article("Cable", availableState, 10);
        article.setId(articleId);

        when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));

        Order savedOrder = new Order(clientId, article, quantity);
        when(orderRepository.save(any())).thenReturn(savedOrder);

        Order result = orderUseCase.createOrder(new CreateOrderRequestModel(articleId, quantity, clientId));

        verify(stockService).checkAndDecrementStock(article, quantity);
        verify(orderRepository).save(any(Order.class));
        verify(articleRepository).save(article);

        assertNotNull(result);
        assertEquals(clientId, result.getClientId());
        assertEquals(1, result.getLines().size());
        assertEquals(quantity, result.getLines().get(0).getQuantity());
        assertEquals(articleId, result.getLines().get(0).getArticleId());
        assertEquals(8, article.getStock());
    }

    @Test
    void shouldThrowIfArticleNotFound() {
        when(articleRepository.findById(42L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            orderUseCase.createOrder(new CreateOrderRequestModel(42L, 3, 1L));
        });

        verify(stockService, never()).checkAndDecrementStock(any(), anyInt());
    }
}