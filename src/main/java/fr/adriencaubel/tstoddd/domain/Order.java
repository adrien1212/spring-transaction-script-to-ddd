package fr.adriencaubel.tstoddd.domain;

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

    public Order(Long clientId) {
        this.clientId = clientId;
        this.createdAt = LocalDateTime.now();
    }

    protected Order() {

    }
}
