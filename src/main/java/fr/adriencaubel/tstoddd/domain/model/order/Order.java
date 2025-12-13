package fr.adriencaubel.tstoddd.domain.model.order;

import fr.adriencaubel.tstoddd.domain.model.article.Article;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Long clientId;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<OrderLine> lines = new ArrayList<>();

    public Order(Long clientId, Article article, Integer quantity) {
        this.clientId = clientId;
        this.createdAt = LocalDateTime.now();
        addLine(article, quantity);
    }

    protected Order() {
        // for JPA
    }

    public void addLine(Article article, Integer quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        if(article == null) {
            throw new IllegalArgumentException("Article cannot be null");
        }

        if(!article.isAvailableToSell()) {
            throw new IllegalArgumentException("Article is not available to sell");
        }

        OrderLine orderLine = new OrderLine(this, article.getId(), quantity);
        lines.add(orderLine);
    }
}
