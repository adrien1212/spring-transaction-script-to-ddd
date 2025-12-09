package fr.adriencaubel.tstoddd.service;

import fr.adriencaubel.tstoddd.controller.dto.CreateOrderRequestModel;
import fr.adriencaubel.tstoddd.domain.Article;
import fr.adriencaubel.tstoddd.domain.Order;
import fr.adriencaubel.tstoddd.domain.OrderLine;
import fr.adriencaubel.tstoddd.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ArticleService articleService;

    @Transactional
    public void createOrder(CreateOrderRequestModel requestModel) {
        // Règle 2 : Vérifier que le client existe
        if(!clientService.existsById(requestModel.clientId())) {
            throw new IllegalArgumentException("Client inconnu : " + requestModel.clientId());
        }

        // Récupérer l'article
        Article article = articleService.findById(requestModel.articleId());

        // Règle 1 : Vérifier que l'article est disponible
        if (!article.getMaterialState().getValue().equals("AVAILABLE")) {
            throw new IllegalStateException("Article non disponible : " + requestModel.articleId());
        }

        // Règle 3 : Décrémenter le stock
        if (article.getStock() < requestModel.quantity()) {
            throw new IllegalStateException("Stock insuffisant pour l'article : " + requestModel.articleId());
        }
        article.setStock(article.getStock() - requestModel.quantity());

        // Créer la ligne de commande
        Order order = new Order(requestModel.clientId());
        OrderLine line = new OrderLine(order, requestModel.articleId(), requestModel.quantity());
        order.getLines().add(line);

        orderRepository.save(order);
    }
}
