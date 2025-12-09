package fr.adriencaubel.tstoddd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_lines")
@Getter
@Setter
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private int quantity;

    protected OrderLine() {}

    public OrderLine(Order order, Long articleId, int quantity) {
        this.order = order;
        this.articleId = articleId;
        this.quantity = quantity;
    }
}
