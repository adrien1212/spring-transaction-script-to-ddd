package fr.adriencaubel.tstoddd.domain.service;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockManagementService {

    private final SupplierService supplierService;

    /**
     * Ensures the article is available and decrements stock.
     * @throws IllegalStateException if stock cannot be decremented
     */
    public void checkAndDecrementStock(Article article, int quantity) {
        if (article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }

        if (!article.isAvailableToSell()) {
            throw new IllegalStateException("Article is not available for sale");
        }

        if(article.getStock() < 5) {
            supplierService.passCommand(article);
        }

        article.decrementStock(quantity); // Aggregate performs the state change
    }
}
