package fr.adriencaubel.tstoddd.domain;

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
}
