package fr.adriencaubel.tstoddd.domain.model.article;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "articles")
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;

    @ManyToOne
    private MaterialState materialState;

    @Column(nullable = false)
    private int stock;

    protected Article() {}

    public Article(String label, MaterialState materialState, int stock) {
        this.label = label;
        this.materialState = materialState;
        this.stock = stock;
    }

    public boolean isAvailableToSell() {
        return this.materialState.isAvailable() && this.stock > 0;
    }

    public void decrementStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (this.stock < amount) {
            throw new IllegalArgumentException("No longer in stock");
        }
        if(!materialState.isAvailable()) {
            throw new IllegalArgumentException("Article is not available to be sold");
        }
        this.stock -= amount;
    }
}
