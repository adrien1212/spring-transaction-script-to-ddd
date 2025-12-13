package fr.adriencaubel.tstoddd.application.order;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import fr.adriencaubel.tstoddd.domain.model.order.Order;
import fr.adriencaubel.tstoddd.domain.service.StockManagementService;
import fr.adriencaubel.tstoddd.infrastructure.db.ArticleRepository;
import fr.adriencaubel.tstoddd.infrastructure.db.OrderRepository;
import fr.adriencaubel.tstoddd.infrastructure.web.dto.CreateOrderRequestModel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderUseCase {

    private final OrderRepository orderRepository;

    private final ArticleRepository articleRepository;

    private final StockManagementService stockManagementService;

    @Transactional
    public Order createOrder(CreateOrderRequestModel createOrderRequestModel) {
        // 1. Load Article aggregate
        Article article = articleRepository.findById(createOrderRequestModel.articleId())
                .orElseThrow(() -> new IllegalArgumentException("Article not found"));

        // 2. Use domain service to validate and decrement stock
        stockManagementService.checkAndDecrementStock(article, createOrderRequestModel.quantity());

        // 3. Create Order aggregate
        Order order = new Order(createOrderRequestModel.clientId(), article, createOrderRequestModel.quantity());

        // 4. Persist aggregates
        orderRepository.save(order);
        articleRepository.save(article);

        return order;
    }
}
