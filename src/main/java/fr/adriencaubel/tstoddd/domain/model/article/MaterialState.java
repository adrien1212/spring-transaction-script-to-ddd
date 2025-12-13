package fr.adriencaubel.tstoddd.domain.model.article;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MaterialState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String value;

    protected MaterialState() {}

    public MaterialState(String value) {
        this.value = value;
    }

    public String getValue() { return value; }

    public boolean isAvailable() {
        return "AVAILABLE".equalsIgnoreCase(value);
    }
}
